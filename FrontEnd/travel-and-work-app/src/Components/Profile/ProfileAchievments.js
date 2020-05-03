import React from "react";
import { Container,Row,Col } from "react-bootstrap";
import AchievmentModal from "../SmartGadgets/AchievementModal";

class ProfileAchievments extends React.Component {
    state = {achievements_special:[],certifications:[],achievements_categorized:[]};

    async componentDidMount() {
        /*const response = await fetch(
            `http://localhost:8080/trip/` + this.props.match.params.id
        );
        const data = await response.json();*/
        console.log("DDD");
        this.setState(
            {achievements_special:[
                {id: 2, name: "Kuchař ryby fugu", description: "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", icon: "fish", recieved_via_enrollments: Array(0)},
                {id: 3, name: "Kuchař ryby fugu", description: "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", icon: "fish", recieved_via_enrollments: Array(0)},
                {id: 4, name: "Horolezec", description: "Uživatel má zkušenosti s lezením po skalách.", icon: "mountain", recieved_via_enrollments: Array(0)}
            ]});
            this.setState({certifications : [
                {id: 2, name: "Certifikát Angličtina B2", description: "Uživatel má certifikát B2 v anglickém jazyku.", icon: "graduation-cap"}
            ]});
            this.setState({achievements_categorized : [
                {id: 3, name: "Kuchtík", description: "Uživatel byl jednou vařit.", icon: "hamburger"}
            ]});
    }
    render() {
        console.log("achievments");
        console.log(this.state.achievements_special);
        return <Container className="userAchievements">
            <Row>
                <div className="window radius fullwidth achievements">
                <h5>Special</h5>
                <p>Aliquam ante. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Etiam commodo dui eget wisi. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonummy</p>
                    {this.state.achievements_special.map(achievement => 
                        <div className="achievement">
                            <div className="circle">
                                <AchievmentModal icon={achievement.icon}
                                title={achievement.name}
                                description={achievement.description} />
                            </div>
                            <span>{achievement.name}</span>
                        </div>
                    )}
                    <h6>Collected 3/15</h6>
                </div>
            </Row>
            <Row>
                <div className="window radius fullwidth achievements">
                <h5>Categorized</h5>
                <p>Ccillum dolore eu fugiat nulla pariatur. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonumm rutrumrut nulla, ullamcorper nec rum y</p>
                    {this.state.achievements_categorized.map(achievement => 
                        <div className="achievement">
                            <div className="circle">
                                <AchievmentModal icon={achievement.icon}
                                title={achievement.name}
                                description={achievement.description} />
                            </div>
                            <span>{achievement.name}</span>
                        </div>
                    )}
                    <h6>Collected 1/8</h6>
                </div>
            </Row>
            <Row>
            <div className="window radius fullwidth achievements">
                <h5>Certifications</h5>
                <p>Aliquam ante. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sedit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                    {this.state.certifications.map(achievement => 
                        <div className="achievement">
                            <div className="circle">
                                <AchievmentModal icon={achievement.icon}
                                title={achievement.name}
                                description={achievement.description} />
                            </div>
                            <span>{achievement.name}</span>
                        </div>
                    )}
                    <h6>Collected 1/5</h6>
                </div>
            </Row>
        </Container>;
    }
}
export default ProfileAchievments;
