package cz.cvut.fel.rsp.travelandwork.service.security;

import cz.cvut.fel.rsp.travelandwork.dao.UserDao;
import cz.cvut.fel.rsp.travelandwork.exception.BadPassword;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccessService {
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private UserDao userDao;

    @Autowired
    public AccessService(PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, UserDao userDao) {

        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.userDao = userDao;
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword, String newPasswordAgain) throws BadPassword {

        final User currentUser = SecurityUtils.getCurrentUser();
        if (!passwordEncoder.matches(oldPassword, userDao.find(currentUser.getId()).getPassword())) {
            throw new BadPassword();
        }else {
            if (newPassword.equals(newPasswordAgain)) {
                currentUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(currentUser.getEmail());
                msg.setSubject("Password change");
                msg.setText("Hello your password has been changed.\n" + "If you did not change it, contact us as soon as possible." +
                        "\n \n With love IT team.");
                javaMailSender.send(msg);
                userDao.update(currentUser);
            }
        }
    }
}
