import React, { useEffect, useState } from "react";
import Lottie from "lottie-react";
import transfer from "../../assets/transfer.json";
import { useNavigate, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";

function Transfering() {
  const location = useLocation();
  const account = useSelector((state) => state.account);
  const sendMoney = location.state.money;
  const navigate = useNavigate();

  const receiverName = location.state.userName;
  const [sended, setSended] = useState(false);
  const [isbutton, setIsButton] = useState(false);
  function checkSended() {
    setSended(true);
  }
  function checkvisible() {
    setIsButton(true);
  }

  const goToDetail = () => {
    if (account.company.code === 106) {
      navigate("/accountBook");
    } else {
      navigate("/accountDetail");
    }
  };

  useEffect(() => {
    setTimeout(() => {
      checkSended();
      checkvisible();
    }, 3000);
  });
  return (
    <>
      <Lottie animationData={transfer} loop={true} />
      <div>{receiverName}님께</div>
      {sended ? (
        <div>{sendMoney}원을 송금했어요</div>
      ) : (
        <div>{sendMoney}원을 송금중이에요</div>
      )}
      {isbutton ? (
        <button
          style={{
            width: "360px",
            backgroundColor: "#7165E3",
            color: "white",
            // position: "relative",
            // left: "0",
            // top: "130px",
          }}
          onClick={() => goToDetail()}
        >
          확인
        </button>
      ) : (
        <></>
      )}
    </>
  );
}

export default Transfering;
