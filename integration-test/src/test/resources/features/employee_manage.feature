# language: ja

機能: 従業員情報管理

  シナリオ:ルートURLにアクセスできる
    前提適切なBaseURIが指定されている
    もしルートURLにアクセスする
    ならばHTTPステータスコードとして200が返却される

  シナリオ:従業員情報を一覧できる
    前提適切なBaseURIが指定されている
    もしすべての従業員情報を取得する
    ならばHTTPステータスコードとして200が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[ID、名字、名前]がリストで返却される
