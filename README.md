<img src = "https://user-images.githubusercontent.com/42319300/122390838-5e1a3600-cfad-11eb-94e3-032da0e0c5b0.png" width ="400" /> <img src = "https://user-images.githubusercontent.com/42319300/122390845-61152680-cfad-11eb-9480-7c4dafb4306e.png" width ="400" /> </br>
<img src = "https://user-images.githubusercontent.com/42319300/122390863-66727100-cfad-11eb-953b-2b3502042671.png" width ="400" /> <img src = "https://user-images.githubusercontent.com/42319300/122390879-6a9e8e80-cfad-11eb-9438-08f90e85558b.png" width ="400" />


# OS_Term_Project
 OS CPU 스케줄링 알고리즘 시뮬레이터


## 👬 팀원
  - [박형민](https://github.com/thalals/)
  - [한성민](https://github.com/songmin9813/)

## ⚙️ 개발 언어
  - 사용언어 : JAVA

## 📜 라이브러리
  - GUI :Java Swing
  - 간트차트 : JFreeChart
----

### 1. 스케줄링 알고리즘 구성도
<img src="https://user-images.githubusercontent.com/42319300/122389594-0deea400-cfac-11eb-8060-f509b1c931bf.png">


### 2. 구현 기능
- FCFS, SJF, 비선점 Priority, 선점 Priority, RR, SRT, HRN
- 사용자 입력에 따른 간트차트

### 3. 프로그램 조직도
1.	FCFS
    - 프로세스가 큐에 도착한 순서대로 실행된다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.
 
2.	SJF
    - 프로세스 작업을 바꿀 때 서비스시간이 가장 작은 프로세스를 실행한다.
    - 서비스시간을 이용하여 프로세스 리스트를 정렬한다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.

3.	HRN
    - SJF 알고리즘에서 우선순위를 계산하여 아사현상을 해결한다.
    - 주어진 공식에 의해 우선순위를 정립하고, 우선순위에 의해 정렬시킨다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.

4.	RR
    - 시간 할당량을 의미하는 변수가 존재한다.
     - 시간 할당량에 의해 프로세스를 실행시킨다.
    - 시간 할당량이 지나도 서비스가 끝나지 않으면 리스트 마지막 순서로 들어간다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.
  
5. SRT
    - RR 에 SJF 기법을 적용하였다.
    -시간 할당량에 의해 프로세스를 실행시킨다.
    - 시간 할당량이 지나고 다음 프로세스를 선택하기 전에, 작업시간이 적은 순서대로 리스트를 정렬하고 첫번째 인덱스를 실행시킨다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.

6. NoPrio (비선점 우선순위)
    - 우선순위에 의해 정렬이 된다.
    - CPU 한번 적재된 프로세스는 끝날 때 까지 실행된다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.

7.	PrPrio (선점 우선순위)
    - 프로세스의 도착 시점을 기준으로 본다.
    - 새로운 프로세스가 도착하면CPU 적재되어 실행중인 프로세스와 우선순위를 비교한다.
    - 새로 도착한 프로세스의 우선순위가 더 높다면, 기존의 프로세스 대신 새 프로세스가 CPU에 적재된다.
    - insert() 함수를 통해 프로세스 리스트를 받아 온다.
    - start() 함수를 통해 알고리즘이 실행되며 로직에 따라 결과를 계산한다.
    - 별도의 출력 함수가 존재한다.
