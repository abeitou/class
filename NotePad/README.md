# NotePad
Android期中实验

实现了两个基本要求和三个附加功能

三个附加功能分别是：

**1.UI美化**

**2.更改笔记背景**

**3.快捷记录**

------



## **基本要求**

#### 1.笔记时间戳

页面截图

![](https://i.loli.net/2018/06/04/5b1501585e266.jpg)

技术实现

因为数据库中已有时间字段，所以只需要格式化时间存入即可 NoteEditor.updateNote()函数中修改 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, df.format(new Date()));

#### 2.笔记查询

页面截图

![](https://i.loli.net/2018/06/04/5b15018e466cc.jpg)

![](https://i.loli.net/2018/06/04/5b1501ade2e60.jpg)

技术实现

```
private void addSearchView() { //给listview添加头部(search)

    View v=View.inflate(this, R.layout.notelistheader,null);
    getListView().addHeaderView(v);
    //给搜索框添加搜索功能
    final EditText et_Search=(EditText)v.findViewById(R.id.et_search);
    et_Search.addTextChangedListener(new TextWatcherForSearch(){
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            super.onTextChanged(charSequence, i, i1, i2);
            if (charSequence.length()!=0 && et_Search.getText().toString().length()!=0){
                String str_Search = et_Search.getText().toString();
                Cursor search_cursor = managedQuery(
                        getIntent().getData(),            // Use the default content URI for the provider.
                        PROJECTION,                       // Return the note ID and title for each note.
                        NotePad.Notes.COLUMN_NAME_TITLE+" like ?",     //模糊查询数据库
                        new String[]{"%"+str_Search+"%"}, //匹配字符串条件                            // No where clause, therefore no where column values.
                        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                );
                adapter.swapCursor(search_cursor);//用搜索结果的cursor刷新listview

            }else {
                if (cursor!=null)//删除搜索框中的text后刷新listview
                adapter.swapCursor(cursor);//刷新listview
            }
        }
    });
}
```



## **附加功能**

#### 1.UI美化

页面截图

![](https://i.loli.net/2018/06/04/5b1503cf91405.jpg)

技术实现

```
case R.id.blanchedalmond:
                					linearLayout.setBackgroundColor(getResources().getColor(R.color.blanchedalmond));
                return true;

            case R.id.peachpuff:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.peachpuff));
                return true;

            case R.id.cornsilk:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.cornsilk));
                return true;
```

#### 2.更改笔记背景

页面截图

![](https://i.loli.net/2018/06/04/5b150299219e8.jpg)

技术实现

```
AlertDialog.Builder builder=new AlertDialog.Builder(NotesList.this);
    LayoutInflater inflater=getLayoutInflater();
    View view=inflater.inflate(R.layout.dialogcolor,null);
    LinearLayout linearLayout=(LinearLayout) view.findViewById(R.id.dialoglinear);
    final int[] colorArray={
            Color.parseColor("#707070"),//黑
            Color.parseColor("#fcf9a4"),//黄
            Color.parseColor("#abed65"),//绿
            Color.parseColor("#33a9e1"),//蓝
            Color.parseColor("#070707"),//黑
            Color.parseColor("#1cdaef"),//蓝绿
            Color.parseColor("#fa77ab"),//粉色
    };
    for (int i=0;i<7;i++){//动态创建imageview 用以显示不同颜色
        ImageView imageView=new ImageView(NotesList.this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(90,120));
        imageView.setBackgroundColor(colorArray[i]);
        final int finalI = i;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteAttribute.snoteBackground=colorArray[finalI];
                getListView().setBackgroundColor(NoteAttribute.snoteBackground);
                getListView().setBackgroundColor(colorArray[finalI]);
                appbackground
                        .edit()
                        .putInt(NoteAttribute.NOTEBACKGROUND,colorArray[finalI])
                        .apply();//使用轻量级存储sharepreference异步将颜色存储在本地
            }
        });
        linearLayout.addView(imageView);
    }
    builder.setView(view).create().show();
```

#### 3.快捷记录（点击粘贴后自动生成一个新的笔记并将粘贴板里内容记录下来）

页面截图

![](https://i.loli.net/2018/06/04/5b15041462166.jpg)

技术实现

```
private final void performPaste() {

        // Gets a handle to the Clipboard Manager
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);

        // Gets a content resolver instance
        ContentResolver cr = getContentResolver();

        // Gets the clipboard data from the clipboard
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null) {

            String text=null;
            String title=null;

            // Gets the first item from the clipboard data
            ClipData.Item item = clip.getItemAt(0);

            // Tries to get the item's contents as a URI pointing to a note
            Uri uri = item.getUri();

            // Tests to see that the item actually is an URI, and that the URI
            // is a content URI pointing to a provider whose MIME type is the same
            // as the MIME type supported by the Note pad provider.
            if (uri != null && NotePad.Notes.CONTENT_ITEM_TYPE.equals(cr.getType(uri))) {

                // The clipboard holds a reference to data with a note MIME type. This copies it.
                Cursor orig = cr.query(
                        uri,            // URI for the content provider
                        PROJECTION,     // Get the columns referred to in the projection
                        null,           // No selection variables
                        null,           // No selection variables, so no criteria are needed
                        null            // Use the default sort order
                );

                // If the Cursor is not null, and it contains at least one record
                // (moveToFirst() returns true), then this gets the note data from it.
                if (orig != null) {
                    if (orig.moveToFirst()) {
                        int colNoteIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_NOTE);
                        int colTitleIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_TITLE);
                        text = orig.getString(colNoteIndex);
                        title = orig.getString(colTitleIndex);
                    }

                    // Closes the cursor.
                    orig.close();
                }
            }

            // If the contents of the clipboard wasn't a reference to a note, then
            // this converts whatever it is to text.
            if (text == null) {
                text = item.coerceToText(this).toString();
            }

            // Updates the current note with the retrieved title and text.
            updateNote(text, title);
        }
    }
```

