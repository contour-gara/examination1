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

  シナリオ:指定したIDの従業員情報が取得できる
    前提適切なBaseURIが指定されている
    もしIDを指定して従業員情報を取得する
    ならばHTTPステータスコードとして200が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[ID、名字、名前]がオブジェクトで返却される

  シナリオ:存在しないIDで従業員情報を取得すると、エラーが返却される
    前提適切なBaseURIが指定されている
    もし存在しないIDで従業員情報を取得する
    ならばHTTPステータスコードとして400が返却される
    かつcodeとして"0003"が返却される
    かつmessageとして"specified employee [id = %s] is not found."が返却される
    かつdetailsとして空のリストが返却される

  シナリオ:従業員情報を登録できる
    前提適切なBaseURIが指定されている
    もし従業員情報を登録する
      | firstName | lastName |
      | Hanako    | Shirato  |
    ならばHTTPステータスコードとして201が返却される
    かつLocationとして登録した従業員情報にアクセスするURLが返却される
    かつ空のBodyが返却される

  シナリオ:必須項目が指定されていない場合、従業員登録が失敗する
    前提適切なBaseURIが指定されている
    もし空の従業員情報を登録する
    ならばHTTPステータスコードとして400が返却される
    かつcodeとして"0002"が返却される
    かつmessageとして"request validation error is occurred."が返却される
    かつdetailsとして詳細なエラー内容を含むリストが返却される

  シナリオ:従業員情報を変更できる
    前提適切なBaseURIが指定されている
    もし従業員情報を変更する
      | firstName | lastName |
      | Bill      | Gates    |
    ならばHTTPステータスコードとして204が返却される
    かつ空のBodyが返却される
    かつ従業員情報が変更されている
      | firstName | lastName |
      | Bill      | Gates    |

  シナリオ:存在しないIDで従業員情報を変更すると、エラーが返却される
    前提適切なBaseURIが指定されている
    もし存在しないIDで従業員情報を変更する
      | firstName | lastName |
      | Bill      | Gates    |
    ならばHTTPステータスコードとして400が返却される
    かつcodeとして"0003"が返却される
    かつmessageとして"specified employee [id = %s] is not found."が返却される
    かつdetailsとして空のリストが返却される

  シナリオ:指定したIDの従業員情報を削除できる
    前提適切なBaseURIが指定されている
    もしIDを指定して従業員情報を削除する
    ならばHTTPステータスコードとして204が返却される
    かつ空のBodyが返却される
    かつ従業員情報が削除されている

  シナリオ:存在しないIDで従業員情報を削除すると、エラーが返却される
    前提適切なBaseURIが指定されている
    もし存在しないIDで従業員情報を削除する
    ならばHTTPステータスコードとして400が返却される
    かつcodeとして"0003"が返却される
    かつmessageとして"specified employee [id = %s] is not found."が返却される
    かつdetailsとして空のリストが返却される
