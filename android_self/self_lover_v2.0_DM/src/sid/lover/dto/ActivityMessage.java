package sid.lover.dto;

import java.util.Date;

public class ActivityMessage {
	// Text
	public static final int MESSAGE_TYPE_TEXT = 1;
	// image
	public static final int MESSAGE_TYPE_IMG = 2;
	private int authorAvatar;//设置最左边的作者头像
	private int commentAvatar;//设置评论的作者头像
	private int type;		//设置当前信息是否是图片信息
	private String title;	//消息标题
	private String story;	//故事详情
	private String imagePath;//图片地址
	private Date date = null;//故事发生时间
	
	//empty used for loading header
	public ActivityMessage(){
	}
	
	/**
	 * 设置文字纪念日
	 * @param authorAvatar
	 * @param title
	 * @param story
	 * @param realtime
	 */
	public ActivityMessage(int authorAvatar,int commentAvatar, String title, String story, long realtime) {
		this.type = MESSAGE_TYPE_TEXT;
		this.authorAvatar = authorAvatar;
		this.title = title;
		this.story = story;
		this.date = new Date(realtime);
		this.commentAvatar = commentAvatar;
	}

	/**
	 * 设置图片纪念日
	 * @param authorAvatar
	 * @param imagePath
	 * @param title
	 * @param story
	 * @param realtime
	 */
	public ActivityMessage(int authorAvatar,int commentAvatar,String imagePath, String title, String story, long realtime) {
		this.type = MESSAGE_TYPE_IMG;
		this.authorAvatar = authorAvatar;
		this.title = title;
		this.story = story;
		this.imagePath = imagePath;
		this.date = new Date(realtime);
		this.commentAvatar = commentAvatar;
	}
	
	public int getHour(){
		if(date!=null)
			return date.getHours();
		return 0;
	}
	public int getMin(){
		if(date!=null)
			return date.getMinutes();
		return 0;
	}
	public int getAuthorAvatar() {
		return authorAvatar;
	}
	public void setAuthorAvatar(int authorAvatar) {
		this.authorAvatar = authorAvatar;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDate() {
		if (date==null) {
			return new Date();
		}
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCommentAvatar() {
		return commentAvatar;
	}

	public void setCommentAvatar(int commentAvatar) {
		this.commentAvatar = commentAvatar;
	}
}
