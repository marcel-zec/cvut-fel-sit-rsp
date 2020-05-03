import React from "react";
import Profile from "../Profile";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Container, Button } from "react-bootstrap";
import TripHistory from "./TripHistory";
import ActiveTrips from "./ActiveTrips";
import Spinner from "react-bootstrap/Spinner";



function ConfirmPayment(props){    
    return (<div className="window radius pay_deposit customScroll">
            <h5>Pay deposit</h5>
            <p>Do you really want to pay the deposit?</p>
            <p>Price: <span id="priceToPay">{props.price}</span>,-</p>
            <div>
                <Button className="submit" onClick={event => props.payFunc(props.state)}>Yes</Button>
                <Button className="cancel" onClick={event => props.component.closeValidateWindow()}>No</Button>
            </div>
        </div>);
}
function CancelTripForm(props){    
    return (<div className="window radius pay_deposit customScroll">
            <h5>Cancel trip</h5>
            <p>Do you really want cancel the trip?</p>
            <p>This operation cannot be undone</p>
            <div>
                <Button className="submit" onClick={event => props.component.cancelTrip(props.state)}>Yes</Button>
                <Button className="cancel" onClick={event => props.component.closeValidateWindow()}>No</Button>
            </div>
        </div>);
}
class ProfileTrips extends Profile {
    state = {
        tripHistory: [
            {
                name: "Kuchař na Pražském hradě",
                xp: 12,
                tripDate: "1995.12.17 - 25.12.1998",
                achievments: [
                    { id: 1, title: "Aaa", description: "Aaa description" },
                    { id: 2, title: "Bbb", description: "Bbb description" }
                ],
                comment: {
                    commentDate: "1996-02-11",
                    content:
                        "Lorem ipsum damlkd nuids mkoda dainuinjuida odasjuida miodauida danuomodmaoi, dakiokioda daiodsaiojk. Ddajoiiojjiioa dasd mmfydc ahuida 12 djuidjas dajiosda. Cadjis mmhusa miouynouicnsd. :)"
                },
                state:"active",
                deposit:"1000",
                enrollmentDate:"5.7.2020",
                location:"Berlin, Germany",
                depositWasPaided:false
            },
            {
                name: "Samujifds",
                xp: 2,
                tripDate: "1995-04-02",
                achievments: [
                    { id: 3, title: "Ccc", description: "Ccc description" }
                ],
                state:"active",
                deposit:"1350",
                enrollmentDate:"5.7.2020",
                location:"Helsinky, Finland",
                depositWasPaided:false
            },
            {
                name: "Mdsaerrr",
                xp: 15,
                tripDate: "1995-06-19",
                achievments: [
                    { id: 4, title: "Ddd", description: "Ddd description" }
                ],
                comment: {
                    commentDate: "1996-02-11",
                    content:
                        "Lorem ipsum damlkd nuids mkoda dainuinjuida odasjuida miodauida danuomodmaoi, dakiokioda daiodsaiojk. Ddajoiiojjiioa dasd mmfydc ahuida 12 djuidjas dajiosda. Cadjis mmhusa miouynouicnsd. :)"
                },
                state:"canceled",
                location:"Moscow, Russia",
                enrollmentDate:"5.7.2020"
            },
            {
                name: "Ldsamiodsa",
                xp: 8,
                tripDate: "1995-04-15",
                achievments: [],
                state:"active",
                deposit:"1000",
                enrollmentDate:"5.7.2020",
                location:"Lisabon, Portugal",
                depositWasPaided:true
            }
        ],
        viewForm: false
    };
    paymentForm = null
    closeValidateWindow(){
        document.querySelector(".popup_background").classList.add("hidden");
        this.setState({paymentForm:null});
        this.setState({viewForm:false});
    }
    cancelTrip(props){
        console.log(props);
        console.log("rusim");
        setTimeout(function () {
            props.setState({isActive:false});      
            this.closeValidateWindow();      
            //TODO:UPDATE BACKEND
        }.bind(this), 1000);
    }
    switchToActive(target){
        target.classList.add("active");
        document.querySelector("#switchToArchive").classList.remove("active");
        document.querySelector(".archiveTrips").classList.remove("active");
        document.querySelector(".activeTrips").classList.add("active");
    }
    switchToArchive(target){
        target.classList.add("active");
        document.querySelector("#switchToActive").classList.remove("active");
        document.querySelector(".archiveTrips").classList.add("active");
        document.querySelector(".activeTrips").classList.remove("active");
    }
    payDeposit(state){
        console.log(this);
        console.log("PLATIS !!!");
        console.log(state);
        setTimeout(function () {
            state.setState({depositWasPaided:true});
            //TODO:UPDATE BACKEND
        }, 1000);
        this.component.closeValidateWindow();
    }
    openPayWindow(price,trip){
        console.log(this);
        const popup = document.querySelector(".popup_background");
        console.log(trip);
        popup.classList.remove("hidden");
        this.component.paymentForm = <ConfirmPayment payFunc={this.component.payDeposit} state={trip} component={this.component} cancelled={this.component.closeValidateWindow} price={price} />
        this.component.setState({ viewForm: true });
    }
    openCancelWindow(trip){
        console.log(this);
        const popup = document.querySelector(".popup_background");
        console.log(trip);
        popup.classList.remove("hidden");
        this.component.paymentForm = <CancelTripForm state={trip} component={this.component} />
        this.component.setState({ viewForm: true });
    }
    renderActiveTrip(activetrips){
        return activetrips.map(trip => <ActiveTrips trip={trip} funcToPay={this.openPayWindow} funcToCancel={this.openCancelWindow} component={this} />)
    }
    renderArchiveTrip(archiveTrips){
        return archiveTrips.map(trip => <TripHistory trip={trip}/>);
    }
    render() {
        if (this.state.trip === null) {
            return (
                <Container className="p-5">
                    <Spinner animation="border" role="status">
                        <span className="sr-only">Loading...</span>
                    </Spinner>
                </Container>
            );
        } else {
            return (
                <Container>
                    <div className="switch">
                        <span id="switchToActive" className="active" onClick={event => this.switchToActive(event.target)}>Now active</span>
                        <span id="switchToArchive" onClick={event => this.switchToArchive(event.target)}>Archive</span>
                    </div> 
                    <div id="tripsElementBlock">
                        <div className="activeTrips active">
                            {this.renderActiveTrip(this.state.tripHistory.filter(trip => trip.state == "active"))}
                        </div>
                        <div className="archiveTrips">
                            {this.renderArchiveTrip(this.state.tripHistory.filter(trip => trip.state != "active"))}
                        </div>
                    </div>
                    <div className="popup_background hidden">
                    {(this.state.viewForm) ?
                        this.paymentForm : ''}
                    </div>
                </Container>
            );
        }
    }
}
export default ProfileTrips;
