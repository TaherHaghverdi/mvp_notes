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
    //Unique identifier of each note
    private String id;
    //Note's title which is common in both approaches to NoteStruct
    private String title;
    //Contains users String input as note, if NoteStruct is a text note this field can't be empty
    private String text;
    //Shows note container folder
    private String folderId;
    //Array of bytes that contains user paintings.
    private byte[] drawing;
    // checks whether a note is painting or text
    private boolean painting;

    /**
     * Initializes a newly created {@code NoteStruct} object so that it represents.
     *
     * @param cursor The Cursor to database to fetch data.
     */
    public NoteStruct(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(DataContract.NoteEntry._ID));
        title = cursor.getString(cursor.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TITLE));
        text = cursor.getString(cursor.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_TEXT));
        folderId = cursor.getString(cursor.getColumnIndex(DataContract.NoteEntry.COLUMN_FOLDER_ID));
        drawing = cursor.getBlob(cursor.getColumnIndex(DataContract.NoteEntry.COLUMN_NOTE_DRAW));
        painting = text == null;
    }

    /**
     * Generates instances of {@code NoteStruct}  from a Parcel
     */
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

    /**
     * Flatten {@code NoteStruct}  in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.folderId);
        dest.writeByteArray(this.drawing);
    }

    /**
     * Initializes a {@code NoteStruct} object from parcel.
     *
     * @param parcel The Parcel that contains our data.
     */
    protected NoteStruct(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.text = parcel.readString();
        this.folderId = parcel.readString();
        this.drawing = parcel.createByteArray();
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

    public boolean isPainting() {
        return painting;
    }
}
