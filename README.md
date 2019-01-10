# tool
Spring Boot と MySQL を用いた簡易レポートシステム

role が 0 のユーザはログイン後レポートを登録し、コメントを追加できます。

role が 1 のユーザは role が 0 の全ユーザのレポートが参照可能で、コメントを追加できます。


# DB
MySQL を使用しています。

education データベース(utf-8) を作成して application.properties の spring.datasource.username と spring.datasource.password を適宜修正してください。

起動時に Flyway がテーブルを自動作成するため、あらかじめテーブルを作成する必要はありません。

# 実行方法

1. mvn clean package を実行します

1. java -jar tool-0.0.1-SNAPSHOT.jar を実行します

1. http://localhost:9393 にアクセスしてください


