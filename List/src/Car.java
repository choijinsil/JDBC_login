

// Car클래스 객체의 비교
public class Car {
// 맴버변수의 기본값: 
	String carName;
	int wheelNum;
	int velocity;

	// public Car(){} 컴파일러가 기본생성 해주지만 인수값이 있으면 안된다.
	public Car(String carName, int wheelNum, int velocity) {
		super();
		this.carName = carName;
		this.wheelNum = wheelNum;
		this.velocity = velocity;
	}

	public boolean equals(Car c) {

		// ** 문자열 내용 비교
		// s1==s2 : 메모리 비교
		// s1.equals(s2): 값(내용) 비교
		// int su<-- 기본자료형이라 속성자료형

		if (carName == c.carName && wheelNum == c.wheelNum && velocity == c.velocity) {
			return true;
		} else {
			return false;
		}
	}

//	public String toString() { // to~메소드: 변환메소드
//		// <-- 객체의 상태를 확인하는 메소드(필드의 값이 무엇인지를 표현)
//		String str = "이 차는 " + carName + "이고 바퀴의 수는 " + wheelNum + ", 속도는 " + velocity + "입니다.";
//		return str;
//	}
	
	@Override
	public String toString() {
		return "Car [carName=" + carName + ", wheelNum=" + wheelNum + ", velocity=" + velocity + "]";
	}
	

	// 서로다른 차 두가지를 비교하는 방법
	public static void main(String[] args) {

		Car c1 = new Car("소나타", 4, 100); // new다음엔 항상 생성자 호출이 온다.
		Car c2 = new Car("그랜져", 4, 200);
		// ** c1과 c2를 비교
		// 1. 래퍼런스 비교(참조==메모리) 비교 <- 등가연산자(등호)를 통해 비교
		// c1==c2; // 숫자, 논리 비교 <- false가 나온다. 왜? new가 두번 쓰였ㅌ거든! 주소값을 비교하기 때문이야.
		// 메모리 주소가 2개니깐 당연 false가 나올 수밖에

		// Wapper클래스
		// 숫자 자료형 , 기본자료형 8개,
		if (c1 == c2) {
			System.out.println("래퍼런스 같음!!");
		} else {
			System.out.println("레퍼런스 다름!!");
		}
		// 2. 내용 비교
		String result = (c1 == c2) ? "레퍼런스같음" : "레퍼런스 다름";
		System.out.println("결과 : " + result);

		System.out.println(c1.toString());
		System.out.println(c2.equals(c1));

//		// 메인 설명
//		System.out.println("안녕");
//
//		for (int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
//		}
//		System.out.println(args.getClass().getTypeName());
//		System.out.println(args[2] + 300);
//		System.out.println(Integer.parseInt(args[2]) + 300);
	}

	

}
