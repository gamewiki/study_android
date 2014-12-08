
package sid.lover.ui.adapters;

import java.io.File;
import java.util.List;

import sid.lover.AddStoryActivity;
import sid.lover.R;
import sid.lover.dto.ActivityMessage;
import sid.lover.ui.views.CircularImage;
import sid.utils.FileUtils;
import sid.utils.SelfDateUtils;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PublicActivityAdapter extends BaseAdapter {
    protected static final String TAG = "ChattingAdapter";

    private Context context;

    public static final String TITLE = "<font color='#1479ad'>%s</font>";

    private List<ActivityMessage> msgs;

    public PublicActivityAdapter(Context context, List<ActivityMessage> messages) {
        super();
        this.context = context;
        this.msgs = messages;

    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActivityMessage message = msgs.get(position);
        ViewHolder holder;
        if (convertView == null || (holder = (ViewHolder) convertView.getTag()).flag != position) {
            holder = new ViewHolder();
            if (position == 0) {
                holder.flag = position;
                convertView = LayoutInflater.from(context).inflate(R.layout.mixed_feed_cover_row, null);
                ImageView cover = (ImageView) convertView.findViewById(R.id.cover_image);
                cover.setImageResource(R.drawable.cover);
                CircularImage avatar = (CircularImage) convertView.findViewById(R.id.cover_user_photo);
                avatar.setImageResource(R.drawable.gauss02);
                CircularImage lover = (CircularImage) convertView.findViewById(R.id.cover_lover_photo);
                lover.setImageResource(R.drawable.gauss01);

                ImageView addStory = (ImageView) convertView.findViewById(R.id.m_bar_add_story);
                addStory.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent in = new Intent(PublicActivityAdapter.this.context,AddStoryActivity.class);
						PublicActivityAdapter.this.context.startActivity(in);
					}
				});
                
            } else {

                int type = message.getType();
                holder.flag = position;

                // Item layout
                convertView = LayoutInflater.from(context).inflate(R.layout.mixed_feed_activity_item, null);

                // author img
                ImageView authorView = (ImageView) convertView.findViewById(R.id.mixed_feed_author_photo);
                authorView.setImageResource(message.getAuthorAvatar());

                // author text
                TextView authorName = (TextView) convertView.findViewById(R.id.mixed_feed_authorname);
                authorName.setText("");

                // big circle
                ImageView big = (ImageView) convertView.findViewById(R.id.moment_bigdot);

                // big smallcircle
                ImageView smal = (ImageView) convertView.findViewById(R.id.moment_smalldot);

                // image type
//                ImageView imgType = (ImageView) convertView.findViewById(R.id.moment_people_photo);

                // feed type image
                ImageView feed_post_type = (ImageView) convertView.findViewById(R.id.feed_post_type);

                // content layout
                LinearLayout contentLayout = (LinearLayout) convertView.findViewById(R.id.feed_post_body);
                // Text
                if (ActivityMessage.MESSAGE_TYPE_TEXT == type) {
                    big.setVisibility(View.GONE);
                    smal.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(context).inflate(
                            R.layout.moment_thought_partial, null);

                    //设置标题
                    TextView thought_main = (TextView) view.findViewById(R.id.thought_main);
                    String txtstr = String.format(TITLE, message.getTitle());
                    Spanned spt = Html.fromHtml(txtstr);
                    thought_main.setText(spt);

                    //设置纪念日的描述内容comment_body
                    TextView comment_body = (TextView) view.findViewById(R.id.comment_body);
                    comment_body.setText(message.getStory());
                    //设置纪念日的显示时间comment_sub
                    TextView comment_sub = (TextView) view.findViewById(R.id.comment_sub);
                    comment_sub.setText(SelfDateUtils.getDateTime("yyyy-MM-dd", message.getDate()));
                    //设置评论人
                    ImageView comment_profile_photo = (ImageView) view.findViewById(R.id.comment_profile_photo);
                    if (message.getCommentAvatar()==0) {
                        comment_profile_photo.setImageResource(R.drawable.gauss01);
					}else{
                        comment_profile_photo.setImageResource(R.drawable.gauss02);
					}

                    contentLayout.addView(view);

                } else if (ActivityMessage.MESSAGE_TYPE_IMG == type) {// Img
                    smal.setVisibility(View.GONE);
                    big.setVisibility(View.VISIBLE);

                    View view = LayoutInflater.from(context).inflate(R.layout.moment_photo_partial, null);
                    feed_post_type.setImageResource(R.drawable.moment_icn_place);
                    // photo
                    ImageView photoView = (ImageView) view.findViewById(R.id.photo);
                    if (message.getImagePath()!=null&&message.getImagePath().equals(""+R.drawable.coffe_lover)) {
                    	photoView.setImageResource(R.drawable.coffe_lover);
					}else{
	                    File file = new File(FileUtils.getInstance().getStoryFilePath() + message.getImagePath());
	                    Uri uriImage = Uri.fromFile(file);
	                    photoView.setImageURI(uriImage);
					}

                    //设置纪念日的标题
                    TextView comment = (TextView) view.findViewById(R.id.comment);
                    String txtstr = String.format(TITLE, message.getTitle());
                    Spanned spt = Html.fromHtml(txtstr);
                    comment.setText(spt);
                    //设置纪念日的描述内容comment_body
                    TextView comment_body = (TextView) view.findViewById(R.id.comment_body);
                    comment_body.setText(message.getStory());
                    //设置纪念日的显示时间comment_sub
                    TextView comment_sub = (TextView) view.findViewById(R.id.comment_sub);
                    comment_sub.setText(SelfDateUtils.getDateTime("yyyy-MM-dd", message.getDate()));
                    //设置评论人
                    ImageView comment_profile_photo = (ImageView) view.findViewById(R.id.comment_profile_photo);
                    if (message.getCommentAvatar()==0) {
                        comment_profile_photo.setImageResource(R.drawable.gauss01);
					}else if (message.getCommentAvatar()==1) {
                        comment_profile_photo.setImageResource(R.drawable.gauss02);
					}else{
                        comment_profile_photo.setImageResource(R.drawable.gauss03);
					}

                    contentLayout.addView(view);
                } else {
                    smal.setVisibility(View.GONE);
                    big.setVisibility(View.VISIBLE);
                }

            }
            convertView.setTag(holder);

        }

        return convertView;
    }

    static class ViewHolder {
        TextView text;

        TextView time;

        TextView status;

        int flag = -1;
    }

}
