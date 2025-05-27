# Spring Boot Todo Application

シンプルな Todo アプリケーションのバックエンド実装です。

## 技術スタック

- Java 17
- Spring Boot
- Maven

## 機能

- Todo アイテムの一覧取得
- Todo アイテムの作成（予定）
- Todo アイテムの更新（予定）
- Todo アイテムの削除（予定）

## 開発環境のセットアップ

1. リポジトリのクローン

```bash
git clone [リポジトリURL]
```

2. プロジェクトのビルド

```bash
./mvnw clean install
```

3. アプリケーションの起動

```bash
./mvnw spring-boot:run
```

## API 仕様

### GET /

Todo アイテムの一覧を取得します。

レスポンス例:

```json
{
  "todos": [
    {
      "id": 1,
      "title": "買い物リストの作成",
      "description": "週末の買い物に必要なものをリストアップする",
      "createdAt": "2024-03-20T14:30:45.123",
      "updatedAt": "2024-03-21T12:30:45.123"
    }
  ]
}
```
