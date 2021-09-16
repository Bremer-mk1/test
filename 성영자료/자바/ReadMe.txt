음악재생 방법은2가지

1.MediaPlayer
2.SoundPool

SoundPool은 짧은 효과음에 사용한다고 함
테스트 해본 결과로는 샘플로 카톡으로 받은 파일은 안끊어지고 재생 가능했음

MediaPlayer는 SoundPool보다 더 긴 파일을 재생할때 사용한다고 함
테스트 해본 결과로는 샘플로 카톡으로 받은 파일은 안끊어지고 재생 가능했음

공통적으로 최초 앱이 기동했을때의 onCreate 에서는 둘다 재생불가임
SoundPool의 경우는 중간에 끊어짐
MediaPlayer는 재생 안됨

버튼 생성해서 버튼 눌렀을때는 둘다 사용가능

인텐트로 다음 화면에서는 onCreate에서 MediaPlayer 사용 가능했음
SoundPool은 테스트 안해봄

MediaPlayer의 경우는 사용후 릴리즈 해줘야됨
