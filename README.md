# <center> **Android QR Scanner Module for Jetpack Compose**</center>

간단하고 사용하기 쉬운 QR 코드 스캐너 모듈입니다. Jetpack Compose를 위해 설계되었으며, [Camera 모듈](https://github.com/developryu/Camera)(https://github.com/developryu/Camera)을 기반으로 합니다.

## 특징

- Jetpack Compose 지원
- 실시간 QR 코드 스캔
- 직관적인 UI와 애니메이션 효과
  - QR 스캔 영역 표시를 위한 녹색 테두리
  - 스캔 진행 상태를 나타내는 상하 이동 애니메이션 빨간 선
- 카메라 제어 기능
  - 전면/후면 카메라 전환
  - 플래시 ON/OFF
  - 카메라 와이드/일반 모드 전환

## 사용 방법

QR 스캐너를 사용하기 위해서는 `QrScanViewScreen` 컴포저블을 사용하면 됩니다.

```kotlin
@Composable
fun SampleScreen() {
    QrScanViewScreen(
        onQrDetected = { qrValue ->
            // QR 코드가 감지되었을 때의 처리
            println("감지된 QR 코드: $qrValue")
        }
    )
}
```

### 필수 매개변수

- `onQrDetected`: (String) -> Unit
  - QR 코드가 감지되었을 때 호출되는 콜백
  - 감지된 QR 코드의 값이 문자열로 전달됩니다
