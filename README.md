# Android_AsyncTask
# 1. Android Thread
* Android는 단일 입력으로 모든 입력 이벤트 및 작업을 처리하며, UI Thread or Main Thread라고 한다. UI스레드는 동시 작업을 처리 할 수 없다. 한 번에 하나의 이벤트 또는 작업만 처리한다. 데이터베이스 작업, 파일 다운로드,웹 서비스 호출과 같은 장기 실행 작업을 수행하려면, 해당 작업이 완료 될 때까지 응용 프로그램이 중단된다. 다른 작업의 응답을 기다리며 Android의 단일 스레드 모델로 인해서 화면이 응답하지 않기 때문이다. 그래서 UI 스레드에서 장기 실행 작업을 수행하지 않아야한다. 이 문제를 위해서 새 스레드를 만들어야 한다.
# 2. AsyncTask
* Android 프레임 워크로 백그라운드에서 비동기 적으로 수행 해야하는 작업을 처리하기 위한 클래스.(하나의 클래스에서 UI작업을 쉽게 하게 해준다.)
# 3. AsyncTask 메서드
* ___doInBackground() :___ 새로 만들어진 work Thread, background 작업을 할 수 있다.  
* ___onProgressUpdate() :___ background 작업중 중간에 UI Thread에 업데이트 할 수 있다.  
* ___onPreExecute()___ : doInBackground 메소드 전에 UI Thread 실행(초기화 작업).  
* ___onPostExecute()___ : doInBackground 작업 완료 후, 결과가 전달된다.(결과)  
* ___execute()___ : Asynctask 상속 객체 실행 메소드.
* ___publishProgress()___ : doInBackground 작업중 onProgressUpdate 메소드에 접근하기 위한 메소드
* ___onCancelled()___ : Asynctask 객체 종료 메소드.(cancel()로 호출)  
  
이 글을 참조함 : <http://androidjavapoint.blogspot.com/2017/01/understanding-of-asynctask-in-android.html>

    
