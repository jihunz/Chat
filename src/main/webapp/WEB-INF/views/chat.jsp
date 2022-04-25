<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    // WS프로토콜로 url 생성
    const url = "ws://" + window.location.hostname + ":" + window.location.port + "/kopo/chatserver";

    // 웹 소켓 객체 생성
    const socket = new WebSocket(url);

    // 연결 여부를 관리하기 위한 변수 -> send()의 조건으로 사용
    let connect = false;

    // 서버와 연결 되었을 때
    socket.onopen = () => {
        connect = true;
        alert("서버에 연결 되었습니다");
    }

    // 서버와의 연결이 종료 되었을 때
    socket.onclose = () => {
        connect = false;
        alert("서버 연결이 종료 되었습니다");
    }

    // 서버에서 메시지를 받아왔을 때
    socket.onmessage = msg => {
        const chat = document.getElementById("chat");

        console.dir(msg);

        chat.innerHTML += "<li>" + msg.data + "</li>";
    }

    function send() {
        if(connect) {
            const nickname = document.getElementById("nickname");
            const msg = document.getElementById("msg");

            socket.send(nickname.value + ":" + msg.value);
            
            // 메시지 발신 뒤에 input에 있는 기존의 메시지를 제거 -> 사용자 편의 배려
            msg.value = "";
        }
    }
</script>
</head>
<body>
    <div>
        <div>
            <ul id="chat"></ul>
        </div>
    </div>
    <hr>
    <div>
        <label>발신자: </label>
        <input id="nickname" type="text" value="익명">
        <label>메세지: </label>
        <input id="msg" type="text">
    </div>
    <div>
        <button onclick="send()">보내기</button>
    </div>
</body>
</html>