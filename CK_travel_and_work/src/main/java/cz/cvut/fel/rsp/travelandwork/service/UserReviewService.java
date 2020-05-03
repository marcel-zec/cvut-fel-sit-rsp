package cz.cvut.fel.rsp.travelandwork.service;


import cz.cvut.fel.rsp.travelandwork.dao.EnrollmentDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.dao.UserReviewDao;
import cz.cvut.fel.rsp.travelandwork.dto.EnrollmentDto;
import cz.cvut.fel.rsp.travelandwork.dto.UserReviewDto;
import cz.cvut.fel.rsp.travelandwork.exception.NotFoundException;
import cz.cvut.fel.rsp.travelandwork.exception.UnauthorizedException;
import cz.cvut.fel.rsp.travelandwork.model.Enrollment;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.model.UserReview;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserReviewService {

    private final TranslateService translateService;
    private final UserReviewDao userReviewDao;
    private final UserDao userDao;
    private final TripSessionDao tripSessionDao;
    private final EnrollmentDao enrollmentDao;


    @Autowired
    public UserReviewService(TranslateService translateService, UserReviewDao userReviewDao, UserDao userDao, TripSessionDao tripSessionDao, EnrollmentDao enrollmentDao) {

        this.translateService = translateService;
        this.userReviewDao = userReviewDao;
        this.userDao = userDao;
        this.tripSessionDao = tripSessionDao;
        this.enrollmentDao = enrollmentDao;
    }

    @Transactional
    public UserReviewDto find(Long id) {
        Objects.requireNonNull(id);
        return translateService.translateUserReview(userReviewDao.find(id));
    }

    @Transactional
    public List<UserReviewDto> findAll() {
        List<UserReviewDto> userReviewDtos = new ArrayList<>();

        for (UserReview userReview : userReviewDao.findAll()) {
            userReviewDtos.add(translateService.translateUserReview(userReview));
        }
        return userReviewDtos;
    }

    @Transactional
    public List<UserReviewDto> findAllOfUser(Long userId) throws NotFoundException {
        User user = userDao.find(userId);
        if (user == null) throw new NotFoundException();
        if (user.getUserReviews() == null) throw new NotFoundException();

        List<UserReviewDto> userReviewDtos = new ArrayList<>();

        for (UserReview userReview : user.getUserReviews()) {
            userReviewDtos.add(translateService.translateUserReview(userReview));
        }
        return userReviewDtos;
    }

    @Transactional
    public List<UserReviewDto> findAllOfUser() throws UnauthorizedException, NotFoundException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new UnauthorizedException();

        User user = userDao.find(SecurityUtils.getCurrentUser().getId());

        List<UserReviewDto> userReviewDtos = new ArrayList<>();
        if (user.getUserReviews() == null) throw new NotFoundException();

        for (UserReview userReview : user.getUserReviews()) {
            userReviewDtos.add(translateService.translateUserReview(userReview));
        }
        return userReviewDtos;
    }

    @Transactional
    public void create(EnrollmentDto enrollmentDto, User currentUser, Long tripSessionId, UserReview userReview) throws Exception {

        Enrollment enrollment = enrollmentDao.find(enrollmentDto.getId());
        User user = enrollment.getTravelJournal().getUser();
        User current_user = userDao.find(currentUser.getId());
        TripSession tripSession = tripSessionDao.find(tripSessionId);

        if (user == null || tripSession==null) throw new NotFoundException();

        userReview.setUser(user);
        userReview.setAuthor(current_user);
        userReview.setTripSession(tripSession);
        userReviewDao.persist(userReview);
    }
}
