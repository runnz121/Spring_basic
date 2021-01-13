package com.msystech.clone_hospital.enums;


public enum RESPONSE_TYPE {
    JSON,
    HTML,
    MOVE;


    private RESPONSE_TYPE() {


    }
}

//enum class 에 생성자를 써야 하는 경우

/*ex)

public enum ex {

    json("1"),
    html("2"),
    move("3");

    private String text;

    private ex(String text) {
        this.text = text;

    }

}
 이런식으로 해당 값을 넣었을때 1,2,3 이 호출 되게끔 할 경우 필요하다
 */
