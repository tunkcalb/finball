import { useState, useEffect } from "react";
import styles from "./StartGame.module.css"
import { useNavigate } from "react-router-dom";

function StartGame() {
    const [red,setRed]=useState<String>("")
    const [blue,setBlue]=useState("")
    const [green,setGreen]=useState("")
    const [yellow,setYellow]=useState("")
    const [purple,setPurple]=useState("")
    const [white,setWhite]=useState("")
    const [cost,setCost]=useState("")
    const navigate = useNavigate();
    const handleChange = (e) => {
        const name = e.target.name; // input 요소의 name 속성을 가져옵니다.
        const value = e.target.value; // input 요소의 값(value)을 가져옵니다.
        
        // name에 따라 해당하는 상태를 업데이트합니다.
        switch (name) {
          case "cost":
            setCost(value);
            break;
          case "red":
            setRed(value);
            break;
          case "blue":
            setBlue(value);
            break;
          case "green":
            setGreen(value);
            break;
          case "yellow":
            setYellow(value);
            break;
          case "purple":
            setPurple(value);
            break;
          case "white":
            setWhite(value);
            break;
          default:
            break;
        }

    };
    const goToGame=() => {
          const state={cost,Name:{ red, blue, green, yellow, purple, white}}
            console.log(state)
            navigate("/game2", { state })
      }
    
      return (
        <>
          <div className={styles.inputContainer}>
            <input
              className={styles.inputbox}
              type="number"
              name="cost"
              placeholder="지불금액"
              value={cost}
              onChange={handleChange}
            />
            <input
              className={styles.inputbox}
              type="text"
              name="red"
              placeholder="red"
              value={red}
              onChange={handleChange}
            />
            <input
              className={styles.inputbox}
              type="text"
              name="blue"
              placeholder="blue"
              value={blue}
              onChange={handleChange}
            />
            <input
              className={styles.inputbox}
              type="text"
              name="green"
              placeholder="green"
              value={green}
              onChange={handleChange}
            />
            <input
            className={styles.inputbox}
            type="text"
            name="yellow"
            placeholder="yellow"
            value={yellow}
            onChange={handleChange}
            />

            <input
              className={styles.inputbox}
              type="text"
              name="purple"
              placeholder="purple"
              value={purple}
              onChange={handleChange}
            />
            <input
              className={styles.inputbox}
              type="text"
              name="white"
              placeholder="white"
              value={white}
              onChange={handleChange}
            />
            <button onClick={goToGame}>게임하러가기</button>
          </div>
        </>
    )
}

export default StartGame;