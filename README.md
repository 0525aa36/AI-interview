딥러닝을 활용한 모의면접 프로그램
Simulated Interview Program Utilizing Deep Learning

프로젝트 개요
면접 과정에서 많은 이들이 겪는 불안과 공포를 해결하기 위해, 딥러닝 기반의 모의면접 프로그램 “면접의 신” 을 제안합니다. 이 프로그램은 OpenCV 및 STT(Speech-To-Text) 기술을 활용하여 사용자의 언어적, 비언어적 면접 태도를 분석하고 종합 피드백을 제공합니다.

주요 기능
표정 및 시선 분석 (OpenCV)
사용자 영상에서 감정 상태 및 눈맞춤 능력을 실시간으로 분석하여 면접 태도 개선을 지원합니다.

언어 습관 분석 (STT)
사용자의 발화 내용을 텍스트로 변환하여, “음”, “그” 등의 습관어 사용 패턴을 분석하고 피드백합니다.

AI 피드백 시스템
분석 결과를 종합해 사용자가 개선할 점과 강점을 제시합니다.

채용 정보 연동
사람인 API를 통해 실시간 채용 공고를 제공받을 수 있습니다.

커뮤니티 기능
사용자 간 소통이 가능한 게시판, 댓글 기능 제공

개발 환경
항목	내용
언어	Java 17, Python 3.12.3
프레임워크	Spring 3.2.5
라이브러리	OpenCV 4.7.0, TensorFlow 2.15.0, Keras
데이터베이스	MySQL
IDE	IntelliJ Ultimate 2023.3
외부 API	Google Cloud Speech API, 사람인 API, 카카오/네이버/구글 로그인 API

사용된 AI/ML 기술
OpenCV: 실시간 시선 추적 및 표정 분석

STT (Speech-to-Text): 음성을 텍스트로 변환 후 습관어 분석

Keras & TensorFlow: 딥러닝 모델 설계 및 학습

Haar Cascade: 얼굴/표정 검출

Xception 모델: 영상 인식 최적화

NLP: 답변 키워드 분석

🗂 데이터베이스 설계 (요약)
Users: 사용자 기본 정보

InterviewQuestions: 직무별 질문 저장

InterviewRecords: 면접 이력 저장

Feedbacks: 피드백 내용 (표정, 시선, 습관어 등)

CommunityPosts, CommunityComments: 커뮤니티 기능 구현

프로젝트 동작 방식
회원가입 → 로그인

직무 선택 및 카메라/마이크 테스트

AI 모의면접 시작 (5개 질문)

면접 후 자동 분석 및 피드백 제공

피드백 확인 및 커뮤니티 이용

📚 참고문헌
진보라 외 (2020), 표정 인식 및 음성 인식 기술을 활용한 모의면접 애플리케이션

문혜진 외 (2022), 인공지능을 활용한 비동시적 면접 연구의 현황과 과제
