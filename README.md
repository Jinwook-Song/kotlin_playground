# Kotlin Playground

| 프로젝트 기간 | 24.12.11~                                         |
| ------------- | ------------------------------------------------- |
| 프로젝트 목적 | Kotlin, jetpack compose                           |
| Github        | https://github.com/Jinwook-Song/kotlin_playground |
|               |                                                   |

---

- Setup
  **SDKMAN!으로 java, kotilin을 설치하고 버전 관리한다.**
  - **SDKMAN! 설치**
    `curl -s "[https://get.sdkman.io](https://get.sdkman.io/)" | bash`
    `source "$HOME/.sdkman/bin/sdkman-init.sh"`
  - 설치 가능한 java list
    `sdk list java`
    여러 리스트가 나오는데 2024년 12월 11일 기준 [Adoptium Eclipse Temurin 21](https://whichjdk.com/#adoptium-eclipse-temurin)가 추천된다.
    (참고: https://whichjdk.com/
  - java install
    `sdk install java 21.0.5-tem`
  - kotlin install
    `sdk install kotlin` (sdk install kotlin 2.1.0 특정 버전 설치)
  - gradle install
    `sdk install gradle`
  - ~~vscode extensions~~ → 안드로이드 studio를 사용할 예정
    - kotlin
    - code runner
    - kotlin language
    - Language Support for Java(TM) by Red Hat
  - jetbrains-toolbox 설치 → 안드로이드 스튜디오 관리
    `brew install --cask jetbrains-toolbox`
    jetbrains-toolbox
  - 안드로이드 스튜디오 설치
    - sdk 설치
    - emulator 설치
