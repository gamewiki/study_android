/**
 * Project Name:test
 * File Name:Constants.java
 * Package Name:sid.almanac.utils
 * Date:2014年5月17日上午11:31:56
 * Copyright (c) 2014, sid Jenkins All Rights Reserved.
 * 
 *
*/

package sid.utils;


/**
 * ClassName:Constants
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2014年5月17日 上午11:31:56 
 * @author   sid
 * @see 	 
 */
public class Constants {
/*
	public static final List<Activities> programmer = new ArrayList<Activities>();//程序猿
	static{
		programmer.add(new Activities("需求评审", "", "", false));
		programmer.add(new Activities("命名变量\"%v\"", "", "", false));
		programmer.add(new Activities("晚上加班", "晚上是程序员精神最好的时候", "", true));
		programmer.add(new Activities("写单元测试", "写单元测试将减少出错", "写单元测试会降低你的开发效率", false));
		programmer.add(new Activities("写超过%l行的方法", "你的代码组织的很好，长一点没关系", "你的代码将混乱不堪，你自己都看不懂", false));
		programmer.add(new Activities("提交代码", "遇到冲突的几率是最低的", "你遇到的一大堆冲突会让你觉得自己是不是时间穿越了", false));
		programmer.add(new Activities("代码复审", "发现重要问题的几率大大增加", "你什么问题都发现不了，白白浪费时间", false));
		programmer.add(new Activities("开会", "写代码之余放松一下打个盹，有益健康", "小心被扣屎盆子背黑锅", false));
		programmer.add(new Activities("晚上上线", "晚上是程序员精神最好的时候", "你白天已经筋疲力尽了", false));
		programmer.add(new Activities("修复BUG", "你今天对BUG的嗅觉大大提高", "新产生的BUG将比修复的更多", false));
		programmer.add(new Activities("设计评审", "设计评审会议将变成头脑风暴", "人人筋疲力尽，评审就这么过了", false));
		programmer.add(new Activities("申请加薪", "老板今天心情很好", "公司正在考虑裁员", false));
		programmer.add(new Activities("提交辞职申请", "公司找到了一个比你更能干更便宜的家伙，巴不得你赶快滚蛋", "鉴于当前的经济形势，你的下一份工作未必比现在强", false));
		programmer.add(new Activities("面试", "面试官今天心情很好", "面试官不爽，会拿你出气", false));
		programmer.add(new Activities("招人", "你面前这位有成为牛人的潜质", "这人会写程序吗？", false));
		programmer.add(new Activities("跳槽", "该放手时就放手", "鉴于当前的经济形势，你的下一份工作未必比现在强", false));
		programmer.add(new Activities("使用%t", "你看起来更有品位", "别人会觉得你在装逼", false));
		programmer.add(new Activities("重构", "代码质量得到提高", "你很有可能会陷入泥潭", false));
		programmer.add(new Activities("白天上线", "今天白天上线是安全的", "可能导致灾难性后果", false));
	}

	public static final List<Activities> oilman = new ArrayList<Activities>();//铁人王进喜
	static{
	}

	public static final List<Activities>  = new ArrayList<Activities>();//财务狗
	static{
	}

	public static final List<Activities> designer = new ArrayList<Activities>();//射鸡湿
	static{
	}

	public static final List<Activities> doctor = new ArrayList<Activities>();//白衣天使
	static{
	}

	public static final List<Activities> hr = new ArrayList<Activities>();//培训助理
	static{
	}

	public static final List<Activities> planner = new ArrayList<Activities>();//点子牛
	static{
	}

	public static final List<Activities> man = new ArrayList<Activities>();
	static{
		man.add(new Activities("洗澡", "你几天没洗澡了？", "会把设计方面的灵感洗掉", true));
		man.add(new Activities("锻炼一下身体", "你几天没洗澡了？", "能量没消耗多少，吃得却更多", true));
		man.add(new Activities("上微博", "今天发生的事不能错过", "今天的微博充满负能量", true));
		man.add(new Activities("上AB站", "还需要理由吗？", "满屏兄贵亮瞎你的眼", true));
		man.add(new Activities("玩2048", "今天破纪录的几率很高", "除非你想玩到把手机砸了", true));
		man.add(new Activities("打DOTA", "你将有如神助", "你会被虐的很惨", true));
		man.add(new Activities("浏览成人网站", "重拾对生活的信心", "你会心神不宁", true));
		man.add(new Activities("在妹子面前吹牛", "改善你矮穷挫的形象", "会被识破", true));
		man.add(new Activities("撸管", "避免缓冲区溢出", "强撸灰飞烟灭", true));
		man.add(new Activities("打拳皇", "上下左右ABCD，遇到的都是菜逼", "被虐的惨不忍睹，不忍直视", true));
		man.add(new Activities("玩手游", "信号让你感觉是在飞", "别白费力气了，你就是举过头顶也他妈没信号", true));
		man.add(new Activities("打扫屋内卫生", "打扫完成你的心情会很好", "放弃吧，猪圈一般", true));
		man.add(new Activities("KTV", "朋友会请客", "全场里面都是山歌", true));

	}

	public static final List<Activities> woman = new ArrayList<Activities>();
	static{
		woman.add(new Activities("锻炼一下身体", "你几天没洗澡了？", "能量没消耗多少，吃得却更多", true));
		woman.add(new Activities("上微博", "今天发生的事不能错过", "今天的微博充满负能量", true));
		woman.add(new Activities("玩2048", "今天破纪录的几率很高", "除非你想玩到把手机砸了", true));
		woman.add(new Activities("抠脚", "心情舒畅，整个人都轻松了好多", "越抠越不爽", true));
		woman.add(new Activities("玩手游", "信号让你感觉是在飞", "别白费力气了，你就是举过头顶也他妈没信号", true));
		woman.add(new Activities("打扫屋内卫生", "打扫完成你的心情会很好", "放弃吧，猪圈一般", true));
		woman.add(new Activities("跟男友见面", "他会领你去吃好吃的", "又要吵架", true));
		woman.add(new Activities("下单", "这宝贝完全跟描述的一样动人", "收到之后发现是个残次品", true));
		woman.add(new Activities("和男票滚床单", "各种高潮", "有可能发生口角", true));
		woman.add(new Activities("逛街", "全是养眼的帅哥", "不止没有帅哥，还会遇到猥琐男", true));
		woman.add(new Activities("KTV", "朋友会请客", "全场里面都是山歌", true));
		woman.add(new Activities("做饭", "心情好，味道好", "油多、盐多，这明显是猪食", true));
		woman.add(new Activities("上淘宝", "看到合心意的东西，物美价廉", "这个月连粥都喝不起了", true));

	}
	
	
	 * 注意：本程序中的“随机”都是伪随机概念，以当前的天为种子。
	 
	public Integer random(Integer dayseed,Integer indexseed) {
		Integer n = dayseed % 11117;
		for (int i = 0; i < 100 + indexseed; i++) {
			n = n * n;
			n = n % 11117;   // 11117 是个质数
		}
		return n;
	}*/
}

