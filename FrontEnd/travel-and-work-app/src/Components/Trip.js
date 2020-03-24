import React from 'react';

class Trip extends React.Component {
  async componentDidMount(){
    const response = await fetch(`http://localhost:8080/trip`);
    const data = await response.json();
    console.log(data);
  }

  render(){
    return (
      <div></div>
     );
  }
  
}

export default Trip;
