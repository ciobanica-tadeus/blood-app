import { Link } from "react-router-dom";
import CardButton from "../components/CardButton";
import classes from './MainPages.module.css'

function Welcome() {
  return (
    <div className={classes.div}>
      <h2>Welcome to the Blood Donation App!</h2>
      <Link to="/login">
        <CardButton text="Login" />
      </Link>
      <Link to="/signup">
        <CardButton text="Sign Up" />
      </Link>
    </div>
  );
}

export default Welcome;
