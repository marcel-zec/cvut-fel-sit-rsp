package cz.cvut.fel.rsp.travelandwork.dto;

import cz.cvut.fel.rsp.travelandwork.model.UserReview;

import javax.validation.constraints.NotBlank;

public class RequestWrapperReview {

    @NotBlank(message = "User id cannot be blank")
    private Long userId;

    @NotBlank(message = "User review cannot be blank")
    private UserReview userReview;

    @NotBlank(message = "Session id cannot be blank")
    private long tripSessionId;


    public RequestWrapperReview(@NotBlank(message = "User id cannot be blank") Long userId, @NotBlank(message = "User review cannot be blank") UserReview userReview,
                                @NotBlank(message = "Session id cannot be blank") long tripSessionId) {

        this.userId = userId;
        this.userReview = userReview;
        this.tripSessionId = tripSessionId;
    }


    public RequestWrapperReview() {
    }


    public long getUserId() {

        return userId;
    }


    public void setUserId(Long userId) {

        this.userId = userId;
    }


    public UserReview getUserReview() {

        return userReview;
    }


    public void setUserReview(UserReview userReview) {

        this.userReview = userReview;
    }


    public long getTripSessionId() {

        return tripSessionId;
    }


    public void setTripSessionId(long tripSessionId) {

        this.tripSessionId = tripSessionId;
    }
}
