import classes from "./Components.module.css";

function CardButton(props) {
  return <div className={classes.card}>
    <button className={classes.prettybutton} onClick={props.onClick}> {props.text} </button>
  </div>;
}

export default CardButton;
