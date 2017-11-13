package com.example.enpit_p3.raise;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by enPiT-P9 on 2017/08/08.
 */

public class Diary extends RealmObject {
    @PrimaryKey
    public long id;
    public String title;
    public String bodyText;
    public String date;
    public byte[] image;
}
