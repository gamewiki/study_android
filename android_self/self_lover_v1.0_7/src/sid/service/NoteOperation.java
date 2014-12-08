package sid.service;

import java.util.Date;
import java.util.List;

import sid.lover.R;
import sid.lover.story.NotesActivity;
import sid.modle.Note;
import sid.utils.AppConstant;
import sid.utils.SelfDateUtils;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class NoteOperation {
	

	/**
	 * 获取数据
	 * @param context
	 * @param type
	 * @param listNotesDes
	 * @param listNotes
	 */
	public static void getNotes(Context context,int type,List<String> listNotesDes,List<Note> listNotes){
		DatabaseHelper dbh = new DatabaseHelper(context,AppConstant.DATABASE, AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		Cursor cursor;
		cursor = sd.query(AppConstant.NOTE,new String[] { "id,createtime,color,type,content" },
				"type=?",new String[] {type+""}, null, null,null);
		while (cursor.moveToNext()) {
			Note note = new Note();
			long id = cursor.getLong(cursor.getColumnIndex("id"));
			String createtime = cursor.getString(cursor.getColumnIndex("createtime"));
			int color = cursor.getInt(cursor.getColumnIndex("color"));
			int notetype = cursor.getInt(cursor.getColumnIndex("type"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			note.setId(id);
			note.setCreatetime(createtime);
			note.setColor(color);
			note.setType(notetype);
			note.setContent(content);
			String text = content.length()>10?(content.substring(0,10)+"..."):content;
			if (!listNotesDes.contains(createtime+"   "+text)) {
				listNotesDes.add(createtime+"   "+text);
				listNotes.add(note);
			}
		}
		cursor.close();
		sd.close();
		dbh.close();
	}
	/**
	 * 删除文件
	 * @param context
	 * @param note
	 * @param deletePersonPosition
	 * @param listNotesDes
	 * @param listNotes
	 */
	public static void deleteNote(Context context,Note note,int deletePersonPosition,List<String> listNotesDes,List<Note> listNotes) {
		System.out.println("=============="+note.getId());
		DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
		SQLiteDatabase sd = dbh.getWritableDatabase();
		sd.delete(AppConstant.NOTE, "id=?", new String[] { note.getId()+"" });
		sd.close();
		dbh.close();
		Toast.makeText(context,context.getString(R.string.note_delete_success), Toast.LENGTH_SHORT).show();
		listNotes.remove(deletePersonPosition);
		listNotesDes.remove(deletePersonPosition);
	}
	

	/**
	 * 添加记录
	 * @param context
	 * @param noteContent
	 * @param noteId
	 * @param color
	 */
	public static void addNote(Context context,String noteContent,String noteId,int color,int type) {
		if (noteContent!=null&&!noteContent.equals("")) {
			boolean exist = true;
			DatabaseHelper dbh = new DatabaseHelper(context, AppConstant.DATABASE,AppConstant.VERSION);
			SQLiteDatabase sd = dbh.getWritableDatabase();
			//判断是否已经存在
			Cursor cursor;
			cursor = sd.query(AppConstant.NOTE,new String[] { "id" },"content=?",
							new String[] {noteContent}, null, null,null);
			while (cursor.moveToNext()) {
				exist = false;
			}
			cursor.close();
			if (exist) {
				//添加便利贴内容
				ContentValues value = new ContentValues();
				value.put("createtime", SelfDateUtils.getDateTime("yyyy-MM-dd HH:mm:ss"));
				value.put("color", color);
				value.put("type", type);
				value.put("content", noteContent);
				if (noteId!=null&&!noteId.equals("")) {
					sd.update(AppConstant.NOTE, value, "id=?", new String[]{noteId});
				} else {
					value.put("id", new Date().getTime());
					sd.insert(AppConstant.NOTE, null, value);
				}
				Toast.makeText(context,
						context.getString(R.string.addNoteSuccess),Toast.LENGTH_SHORT).show();
				sd.close();
				dbh.close();
				noteId = null;
				Intent intent = new Intent();
				intent.putExtra("type", type);
				intent.setClass(context, NotesActivity.class);
				context.startActivity(intent);
			}else{
				sd.close();
				dbh.close();
				Toast.makeText(context,
						context.getString(R.string.NoteExist),Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(context,
					context.getString(R.string.addNoteNull),Toast.LENGTH_SHORT).show();
		}
	}
}
