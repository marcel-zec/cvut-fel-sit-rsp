import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";
import ProgressBar from "react-bootstrap/ProgressBar";
import Image from "react-bootstrap/Image";

import Spinner from "react-bootstrap/Spinner";

class UserReview extends React.Component {
    state={reviews:null};
    async componentDidMount() {
        /*const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data = await response.json();*/
        //console.log(data);
        let data = [{author:"František Omáčka",rating:3.5,date:"7.srpna 2020",note:"Venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."},{author:"Tomáš Omáčka",rating:4.0,date:"7.zari 2020",note:"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Etiam quis quam. Donec iaculis gravida nulla. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Maecenas fermentum, sem in pharetra pellentesque, velit turpis volutpat ante, in pharetra metus odio a lectus. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Pellentesque sapien. Duis pulvinar. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Mauris tincidunt sem sed arcu."}]
        this.setState({reviews:data});
    }
    renderRating(rating){
        let starsElement=[];
        if(rating == 0){
            return <span style={{color: 'black'}}>Trip has not any review</span>;
        }
        for (var i = 1; i <= rating; i++) {
            starsElement.push(<FontAwesomeIcon key={i} icon="star" />);
        }
        if(rating-starsElement.length >= 0.5){
            starsElement.push(<FontAwesomeIcon key={starsElement.length+1} icon="star-half" />);
        }
        return starsElement;
    }
    render() {
        if(this.state.reviews == null){
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        }else{
            return (
            
                <Container style={{width:"750px"}} className="trip_reviews window radius">
                    <div className="summary"><h5 style={{marginTop:"15px"}}>Summary rating: 4/5 <span style={{color:"#fce23a"}}>{this.renderRating(4)}</span></h5></div>
                    {this.state.reviews.map((review) =>
                        <div className="review" style={{marginTop:"25px"}}>
                            <Row>
                                <Col className="rev_author" xs={6}><FontAwesomeIcon icon="user-alt"/><span>{review.author}</span></Col>
                                <Col className="rev_rating" xs={6}>{this.renderRating(review.rating)}</Col>
                            </Row>
                            <Row>
                                <Col><p className="note">{review.note}</p></Col>
                            </Row>
                        </div>
                    )}
    
                </Container>
            );
        }
        
    }
}
export default UserReview;