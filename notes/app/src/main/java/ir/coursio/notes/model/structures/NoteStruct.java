package ir.coursio.notes.model.structures;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import ir.coursio.notes.model.db.DataContract;

/**
 * Created by Taher on 29/05/2017.
 * Project: notes
 */

public class NoteStruct implements Parcelable {
    private String id;
    private String title;
    private String text;
    private String folderId;
    private byte[] drawing;

    public NoteStruct(Cursor data) {
        id= data.getString(data.getColumnIndex(DataContract.NoteEntry._ID));
        title=data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TITLE));
        text =data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TEXT));
        folderId=data.getString(data.getColumnIndex(DataContract.NoteEntry.COLUMN_FOLDER_ID));
        drawing=data.getBlob(data.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_DRAW));
    }


    public static final Parcelable.Creator<NoteStruct> CREATOR = new Parcelable.Creator<NoteStruct>() {
        @Override
        public NoteStruct createFromParcel(Parcel source) {
            return new NoteStruct(source);
        }

        @Override
        public NoteStruct[] newArray(int size) {
            return new NoteStruct[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.folderId);
        dest.writeByteArray(this.drawing);
    }

    protected NoteStruct(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.text = in.readString();
        this.folderId = in.readString();
        this.drawing = in.createByteArray();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getFolderId() {
        return folderId;
    }

    public byte[] getDrawing() {
        return drawing;
    }
}
