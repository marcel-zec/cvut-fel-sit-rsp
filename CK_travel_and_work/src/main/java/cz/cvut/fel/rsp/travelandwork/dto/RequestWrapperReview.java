package cz.cvut.fel.rsp.travelandwork.dto;

import cz.cvut.fel.rsp.travelandwork.model.UserReview;

import javax.validation.constraints.NotNull;

public class RequestWrapperReview {

    @NotNull(message = "User id cannot be blank")
    private Long userId;

    @NotNull(message = "User review cannot be blank")
    private UserReview userReview;

    @NotNull(message = "Session id cannot be blank")
    private Long tripSessionId;


    public RequestWrapperReview(@NotNull(message = "User id cannot be blank") Long userId, @NotNull(message = "User review cannot be blank") UserReview userReview,
                                @NotNull(message = "Session id cannot be blank") Long tripSessionId) {

        this.userId = userId;
        this.userReview = userReview;
        this.tripSessionId = tripSessionId;
    }


    public RequestWrapperReview() {
    }


    public Long getUserId() {

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


    public Long getTripSessionId() {

        return tripSessionId;
    }


    public void setTripSessionId(Long tripSessionId) {

        this.tripSessionId = tripSessionId;
    }
}
