/*
 * 注意：本程序中的“随机”都是伪随机概念，以当前的天为种子。
 */
function random(dayseed, indexseed) {
	var n = dayseed % 11117;
	for (var i = 0; i < 100 + indexseed; i++) {
		n = n * n;
		n = n % 11117;   // 11117 是个质数
	}
	return n;
}

var today = new Date();
var iday = today.getFullYear() * 10000 + (today.getMonth() + 1) * 100 + today.getDate();

var weeks = ["日","一","二","三","四","五","六"];
var directions = ["北方","东北方","东方","东南方","南方","西南方","西方","西北方"];
var programmer_m = [
	{name:"写单元测试", good:"写单元测试将减少出错",bad:"写单元测试会降低你的开发效率"},
	{name:"白天上线", good:"今天白天上线是安全的",bad:"可能导致灾难性后果"},
	{name:"重构", good:"代码质量得到提高",bad:"你很有可能会陷入泥潭"},
	{name:"使用%t", good:"你看起来更有品位",bad:"别人会觉得你在装逼"},
	{name:"跳槽", good:"该放手时就放手",bad:"鉴于当前的经济形势，你的下一份工作未必比现在强"},
	{name:"招人", good:"你面前这位有成为牛人的潜质",bad:"这人会写程序吗？"},
	{name:"面试", good:"面试官今天心情很好",bad:"面试官不爽，会拿你出气"},
	{name:"提交辞职申请", good:"公司找到了一个比你更能干更便宜的家伙，巴不得你赶快滚蛋",bad:"鉴于当前的经济形势，你的下一份工作未必比现在强"},
	{name:"申请加薪", good:"老板今天心情很好",bad:"公司正在考虑裁员"},
	{name:"晚上加班", good:"晚上是程序员精神最好的时候",bad:"", weekend: true},
	{name:"命名变量\"%v\"", good:"",bad:""},
	{name:"写超过%l行的方法", good:"你的代码组织的很好，长一点没关系",bad:"你的代码将混乱不堪，你自己都看不懂"},
	{name:"提交代码", good:"遇到冲突的几率是最低的",bad:"你遇到的一大堆冲突会让你觉得自己是不是时间穿越了"},
	{name:"代码复审", good:"发现重要问题的几率大大增加",bad:"你什么问题都发现不了，白白浪费时间"},
	{name:"开会", good:"写代码之余放松一下打个盹，有益健康",bad:"小心被扣屎盆子背黑锅"},
	{name:"晚上上线", good:"晚上是程序员精神最好的时候",bad:"你白天已经筋疲力尽了"},
	{name:"修复BUG", good:"你今天对BUG的嗅觉大大提高",bad:"新产生的BUG将比修复的更多"},
	{name:"设计评审", good:"设计评审会议将变成头脑风暴",bad:"人人筋疲力尽，评审就这么过了"},
	{name:"需求评审", good:"",bad:""},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
];
var programmer_w = [
	{name:"写单元测试", good:"写单元测试将减少出错",bad:"写单元测试会降低你的开发效率"},
	{name:"白天上线", good:"今天白天上线是安全的",bad:"可能导致灾难性后果"},
	{name:"重构", good:"代码质量得到提高",bad:"你很有可能会陷入泥潭"},
	{name:"使用%t", good:"你看起来更有品位",bad:"别人会觉得你在装逼"},
	{name:"跳槽", good:"该放手时就放手",bad:"鉴于当前的经济形势，你的下一份工作未必比现在强"},
	{name:"招人", good:"你面前这位有成为牛人的潜质",bad:"这人会写程序吗？"},
	{name:"面试", good:"面试官今天心情很好",bad:"面试官不爽，会拿你出气"},
	{name:"提交辞职申请", good:"公司找到了一个比你更能干更便宜的家伙，巴不得你赶快滚蛋",bad:"鉴于当前的经济形势，你的下一份工作未必比现在强"},
	{name:"申请加薪", good:"老板今天心情很好",bad:"公司正在考虑裁员"},
	{name:"晚上加班", good:"晚上是程序员精神最好的时候",bad:"", weekend: true},
	{name:"命名变量\"%v\"", good:"",bad:""},
	{name:"写超过%l行的方法", good:"你的代码组织的很好，长一点没关系",bad:"你的代码将混乱不堪，你自己都看不懂"},
	{name:"提交代码", good:"遇到冲突的几率是最低的",bad:"你遇到的一大堆冲突会让你觉得自己是不是时间穿越了"},
	{name:"代码复审", good:"发现重要问题的几率大大增加",bad:"你什么问题都发现不了，白白浪费时间"},
	{name:"开会", good:"写代码之余放松一下打个盹，有益健康",bad:"小心被扣屎盆子背黑锅"},
	{name:"晚上上线", good:"晚上是程序员精神最好的时候",bad:"你白天已经筋疲力尽了"},
	{name:"修复BUG", good:"你今天对BUG的嗅觉大大提高",bad:"新产生的BUG将比修复的更多"},
	{name:"设计评审", good:"设计评审会议将变成头脑风暴",bad:"人人筋疲力尽，评审就这么过了"},
	{name:"需求评审", good:"",bad:""},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true}
];
var oilman_m = [
    {name:"洗漱着装", good:"水房的水还挺热乎", bad:"尼玛，水房又锁门", },
    {name:"接班上岗", good:"无异常，一切安好", bad:"仪器各种出显示", },
    {name:"监控", good:"数据稳定变化", bad:"这个仪器会不好使，那个传感器又会坏", },
    {name:"看电视剧", good:"无人查岗", bad:"来检查，你可能会被抓", },
    {name:"处理数据", good:"无气测，无异常", bad:"显示数据会像心电图一般乱蹦", },
    {name:"吃午饭", good:"你会发现今天厨子的手艺不错哦", bad:"这哪是人吃的", },
    {name:"捞沙子", good:"量多又易洗白白", bad:"黏糊糊一坨，一坨", },
    {name:"烤沙子", good:"并不是只有厨房才有烤箱，我们的烤箱也很棒", bad:"烤箱又会罢工", },
    {name:"装箱编码", good:"唯一需要思考的就是晚上去哪里Happy", bad:"一箱60袋，出错是猪", },
    {name:"灌泥浆", good:"神手法，神走位，一滴不落", bad:"手抽筋，脚抽筋，埋埋汰汰", },
    {name:"学习", good:"各种指点，不耻下问", bad:"全都码长城，无人屌你", },
    {name:"睡觉", good:"起下钻，安心睡懒觉", bad:"钻进，枕头都在颤抖，天天四级地震", },
    {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
    {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true}
	];
var oilman_w = [
    {name:"洗漱着装", good:"水房的水还挺热乎", bad:"尼玛，水房又锁门", },
    {name:"接班上岗", good:"无异常，一切安好", bad:"仪器各种出显示", },
    {name:"监控", good:"数据稳定变化", bad:"这个仪器会不好使，那个传感器又会坏", },
    {name:"看电视剧", good:"无人查岗", bad:"来检查，你可能会被抓", },
    {name:"处理数据", good:"无气测，无异常", bad:"显示数据会像心电图一般乱蹦", },
    {name:"吃午饭", good:"你会发现今天厨子的手艺不错哦", bad:"这哪是人吃的", },
    {name:"捞沙子", good:"量多又易洗白白", bad:"黏糊糊一坨，一坨", },
    {name:"烤沙子", good:"并不是只有厨房才有烤箱，我们的烤箱也很棒", bad:"烤箱又会罢工", },
    {name:"装箱编码", good:"唯一需要思考的就是晚上去哪里Happy", bad:"一箱60袋，出错是猪", },
    {name:"灌泥浆", good:"神手法，神走位，一滴不落", bad:"手抽筋，脚抽筋，埋埋汰汰", },
    {name:"学习", good:"各种指点，不耻下问", bad:"全都码长城，无人屌你", },
    {name:"睡觉", good:"起下钻，安心睡懒觉", bad:"钻进，枕头都在颤抖，天天四级地震", },
    {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
    {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true}
	];
var finance_m = [
	 {name:"写材料", good:"以前有类似的材料 简单改改还能被夸", bad:"卧槽，新材料，只能自己编", },
	 {name:"科长找", good:"最近一切运营良好，科长心情大好", bad:"妈蛋，又会出问题", },
	 {name:"处理业务", good:"有人知道该怎么处理 告诉你", bad:"都他妈在聊天，没人理你", },
	 {name:"月末结账", good:"凭证没有错误 报表顺利通过", bad:"系统又崩掉，凭证可能有出入，傻逼上级还催着封账！", },
	 {name:"逛淘宝", good:"心仪已久的宝贝降价了", bad:"科长又会来找麻烦", },
	 {name:"办公电话响", good:"有惊无险，是个推销的", bad:"大爷的，又是催做账的", },
	 {name:"整理凭证", good:"什么单据也不缺", bad:"缺了单据或发票，就认准是在你手上丢的", },
	 {name:"来审计", good:"走个形式罢了，莫当真", bad:"想啥呢，赶紧整理材料吧", },
	 {name:"开科务会", good:"就是去喝喝茶，聊聊天", bad:"科长会点名批评", },
	 {name:"学习", good:"各种指点，不耻下问", bad:"全都码长城，无人屌你", },
	 {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
	 {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
	];
var finance_w = [
    {name:"写材料", good:"以前有类似的材料 简单改改还能被夸", bad:"卧槽，新材料，只能自己编", },
    {name:"科长找", good:"最近一切运营良好，科长心情大好", bad:"妈蛋，又会出问题", },
    {name:"处理业务", good:"有人知道该怎么处理 告诉你", bad:"都他妈在聊天，没人理你", },
    {name:"月末结账", good:"凭证没有错误 报表顺利通过", bad:"系统又崩掉，凭证可能有出入，傻逼上级还催着封账！", },
    {name:"逛淘宝", good:"心仪已久的宝贝降价了", bad:"科长又会来找麻烦", },
    {name:"办公电话响", good:"有惊无险，是个推销的", bad:"大爷的，又是催做账的", },
    {name:"整理凭证", good:"什么单据也不缺", bad:"缺了单据或发票，就认准是在你手上丢的", },
    {name:"来审计", good:"走个形式罢了，莫当真", bad:"想啥呢，赶紧整理材料吧", },
    {name:"开科务会", good:"就是去喝喝茶，聊聊天", bad:"科长会点名批评", },
    {name:"学习", good:"各种指点，不耻下问", bad:"全都码长城，无人屌你", },
    {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
    {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true}
	];
var designer_m = [
	 {name:"平面类设计", good:"客户沟通之后顺利制作一次通过", bad:"做完也会再次修改的", },
	 {name:"送印场印刷", good:"没有问题直接印刷", bad:"颜色校准出问题跑印场跟印", },
	 {name:"下班", good:"正常下班和别人一起到家吃饭", bad:"领导会临时通知要加班哦", },
	 {name:"午饭", good:"和三五个同事在公司周围有说有笑的吃美食", bad:"午饭是什么？！", },
	 {name:"改需求", good:"顺利搞定", bad:"小心软件会崩溃！当天做完了也等于没做完一个崩溃全玩儿完", },
	 {name:"同事辞职", good:"老板为了安抚你们会给你们涨工资", bad:"他的工作会落到你头上", },
	 {name:"工作分派", good:"任务量会相对较少哦", bad:"傻逼领导会把大部分任务分给你", },
	 {name:"提交给总监审核", good:"总监心情大好，直接通过", bad:"总监今天心情不好，会劈头盖脸的骂你", },
	 {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
	 {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
		];
var designer_w = [
 	 {name:"平面类设计", good:"客户沟通之后顺利制作一次通过", bad:"做完也会再次修改的", },
	 {name:"送印场印刷", good:"没有问题直接印刷", bad:"颜色校准出问题跑印场跟印", },
	 {name:"下班", good:"正常下班和别人一起到家吃饭", bad:"领导会临时通知要加班哦", },
	 {name:"午饭", good:"和三五个同事在公司周围有说有笑的吃美食", bad:"午饭是什么？！", },
	 {name:"改需求", good:"顺利搞定", bad:"小心软件会崩溃！当天做完了也等于没做完一个崩溃全玩儿完", },
	 {name:"同事辞职", good:"老板为了安抚你们会给你们涨工资", bad:"他的工作会落到你头上", },
	 {name:"工作分派", good:"任务量会相对较少哦", bad:"傻逼领导会把大部分任务分给你", },
	 {name:"提交给总监审核", good:"总监心情大好，直接通过", bad:"总监今天心情不好，会劈头盖脸的骂你", },
	 {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
	 {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true},
	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
	];
var doctor_m = [
    {name:"收新病人", good:"情况好处理，家属会很有礼貌", bad:"病难治，病人不配合，家属还不讲理", },
    {name:"写病历", good:"居然都差不多", bad:"各种不同，完全忙不过来", },
    {name:"开会", good:"院长心情好", bad:"一堆事情没有做不知道几点下班的节奏", },
    {name:"查房", good:"一切顺利", bad:"个别病人病情复杂会交待三个小时以上", },
    {name:"考试", good:"都是你会的", bad:"乱七八糟啊喂  各种不着边", },
    {name:"门诊", good:"正常的病人", bad:"又遇到流氓了", },
    {name:"值夜班", good:"最多24小时", bad:"48小时 人都要劈了", },
    {name:"手术", good:"简单 时间段", bad:"十几个小时也得做 等下来感觉没有腿了", },
    {name:"开研讨会", good:"学到新东西", bad:"妈蛋，政策又变了", },
    {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
    {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
 	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
 	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
 	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
 	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
 	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
 	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
 	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
 	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
 	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
 	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
 	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
 	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
 	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
 	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
 	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
 		];
 var doctor_w = [
	 {name:"收新病人", good:"情况好处理，家属会很有礼貌", bad:"病难治，病人不配合，家属还不讲理", },
	 {name:"写病历", good:"居然都差不多", bad:"各种不同，完全忙不过来", },
	 {name:"开会", good:"院长心情好", bad:"一堆事情没有做不知道几点下班的节奏", },
	 {name:"查房", good:"一切顺利", bad:"个别病人病情复杂会交待三个小时以上", },
	 {name:"考试", good:"都是你会的", bad:"乱七八糟啊喂  各种不着边", },
	 {name:"门诊", good:"正常的病人", bad:"又遇到流氓了", },
	 {name:"值夜班", good:"最多24小时", bad:"48小时 人都要劈了", },
	 {name:"手术", good:"简单 时间段", bad:"十几个小时也得做 等下来感觉没有腿了", },
	 {name:"开研讨会", good:"学到新东西", bad:"妈蛋，政策又变了", },
	 {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
	 {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
 	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
 	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
 	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
 	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
 	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
 	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
 	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
 	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
 	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
 	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
 	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
 	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
 	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
 	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
 	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
 	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
 	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
 	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true}
 	];
 var hr_m = [
    {name:"教导新员工的入职资料填写", good:"员工比较认真的，资料填写清晰", bad:"不听讲授，资料乱写", },
   {name:"新员工规章制度培训", good:"今天思路清晰，记住一本红楼梦都没问题", bad:"思绪混乱，什么都记不住", },
   {name:"需求调查", good:"各部门十分配合，清晰了解部门培训需求", bad:"所有部门都来了大姨妈，不惹为妙", },
   {name:"跟进部门培训开展", good:"今天一切都能搞定", bad:"一拖再拖，难以完成", },
   {name:"培训资料收集", good:"能够及时提供相关资料", bad:"打爆电话，资料才会姗姗来迟，还不符合要求", },
   {name:"效果评估及反馈", good:"评估表按实际填写，访谈顺利，评估有效", bad:"得不到需要的真实情况，评估无效", },
   {name:"培训档案的建立", good:"升值加薪的时候需要", bad:"还没用到培训档案，员工就走人了", },
   {name:"培训实施的反馈", good:"看到本身的不足，下个月改进", bad:"依旧拖着培训不完成", },
   {name:"方圆计划考试", good:"顺利进行", bad:"领导姨妈造访，会劈头盖脸的大骂，需要重新出题", },
   {name:"招人", good:"有帅哥美女看，看着还顺眼", bad:"你敢再蠢一点么？", },
   {name:"做报表", good:"思路清晰，完全没有问题啊", bad:"你敢再蠢一点么？", },
   {name:"上招聘网站", good:"网站中正好有合适的简历，正好可以看到联系方式", bad:"为什么满眼都是猪一样的人", },
   {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
   {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
 	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
  	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
  	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
  	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
  	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
  	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
  	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
  	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
  	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
  	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
  	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
  	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
  	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
  	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
  	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
  		];
var hr_w = [
    {name:"教导新员工的入职资料填写", good:"员工比较认真的，资料填写清晰", bad:"不听讲授，资料乱写", },
    {name:"新员工规章制度培训", good:"今天思路清晰，记住一本红楼梦都没问题", bad:"思绪混乱，什么都记不住", },
    {name:"需求调查", good:"各部门十分配合，清晰了解部门培训需求", bad:"所有部门都来了大姨妈，不惹为妙", },
    {name:"跟进部门培训开展", good:"今天一切都能搞定", bad:"一拖再拖，难以完成", },
    {name:"培训资料收集", good:"能够及时提供相关资料", bad:"打爆电话，资料才会姗姗来迟，还不符合要求", },
    {name:"效果评估及反馈", good:"评估表按实际填写，访谈顺利，评估有效", bad:"得不到需要的真实情况，评估无效", },
    {name:"培训档案的建立", good:"升值加薪的时候需要", bad:"还没用到培训档案，员工就走人了", },
    {name:"培训实施的反馈", good:"看到本身的不足，下个月改进", bad:"依旧拖着培训不完成", },
    {name:"方圆计划考试", good:"顺利进行", bad:"领导姨妈造访，会劈头盖脸的大骂，需要重新出题", },
    {name:"招人", good:"有帅哥美女看，看着还顺眼", bad:"你敢再蠢一点么？", },
    {name:"做报表", good:"思路清晰，完全没有问题啊", bad:"你敢再蠢一点么？", },
    {name:"上招聘网站", good:"网站中正好有合适的简历，正好可以看到联系方式", bad:"为什么满眼都是猪一样的人", },
    {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
    {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
  	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
  	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
  	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
  	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
  	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
  	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
  	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
  	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true},
  	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
  	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
  	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true},
  	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
  	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
  	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
  	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
  	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
  	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
  	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true}
  	];
var planner_m = [
     {name:"写策划案", good:"一次性通过", bad:"领导吃了摇头丸", },
     {name:"开创意会", good:"梳理构架成功", bad:"领导的早上好像忘记刷牙了", },
     {name:"写文案", good:"思路清晰，一致好评", bad:"思绪混乱，一团糟糕，语句不通顺，不会写", },
     {name:"竞标案", good:"彻夜通宵竞标，往往是成功的", bad:"告诉你们领导，最近别瞎忙活了，肯定中不上", },
     {name:"策略分析", good:"以你为主导进行策略梳理", bad:"听那些山炮不停地说，完全不会产生共鸣", },
     {name:"打台球", good:"有准星有手感", bad:"你这个水平五岁小孩你都赢不过的", },
     {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
     {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
   	{name:"洗澡", good:"你几天没洗澡了？",bad:"会把设计方面的灵感洗掉", weekend: true},
   	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
   	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
   	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
   	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
   	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
   	{name:"在妹子面前吹牛", good:"改善你矮穷挫的形象",bad:"会被识破", weekend: true},
   	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
   	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
   	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
   	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
   	{name:"撸管", good:"避免缓冲区溢出",bad:"强撸灰飞烟灭", weekend: true},
   	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
   	{name:"打DOTA", good:"你将有如神助",bad:"你会被虐的很惨", weekend: true},
   	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true}
 ];
 var planner_w = [
     {name:"写策划案", good:"一次性通过", bad:"领导吃了摇头丸", },
     {name:"开创意会", good:"梳理构架成功", bad:"领导的早上好像忘记刷牙了", },
     {name:"写文案", good:"思路清晰，一致好评", bad:"思绪混乱，一团糟糕，语句不通顺，不会写", },
     {name:"竞标案", good:"彻夜通宵竞标，往往是成功的", bad:"告诉你们领导，最近别瞎忙活了，肯定中不上", },
     {name:"策略分析", good:"以你为主导进行策略梳理", bad:"听那些山炮不停地说，完全不会产生共鸣", },
     {name:"打台球", good:"有准星有手感", bad:"你这个水平五岁小孩你都赢不过的", },
     {name:"跳槽", good:"该放手时就放手", bad:"鉴于当前的经济形势，你的下一份工作未必比现在强", },
     {name:"申请加薪", good:"老板今天心情很好", bad:"公司正在考虑裁员", },
   	{name:"上微博", good:"今天发生的事不能错过",bad:"今天的微博充满负能量", weekend: true},
   	{name:"玩FlappyBird", good:"今天破纪录的几率很高",bad:"除非你想玩到把手机砸了", weekend: true},
   	{name:"锻炼一下身体", good:"",bad:"能量没消耗多少，吃得却更多", weekend: true},
   	{name:"抽烟", good:"抽烟有利于提神，增加思维敏捷",bad:"除非你活够了，死得早点没关系", weekend: true},
   	{name:"上AB站", good:"还需要理由吗？",bad:"满屏兄贵亮瞎你的眼", weekend: true},
   	{name:"浏览成人网站", good:"重拾对生活的信心",bad:"你会心神不宁", weekend: true},
   	{name:"玩2048", good:"今天破纪录的几率很高", bad:"除非你想玩到把手机砸了", weekend: true},
   	{name:"KTV", good:"朋友会请客", bad:"全场里面都是山歌", weekend: true},
   	{name:"抠脚", good:"心情舒畅，整个人都轻松了好多", bad:"越抠越不爽", weekend:true},
   	{name:"跟男友见面", good:"他会领你去吃好吃的", bad:"又要吵架", weekend:true},
   	{name:"逛街", good:"全是养眼的帅哥", bad:"不止没有帅哥，还会遇到猥琐男", weekend:true},
   	{name:"做饭", good:"心情好，味道好", bad:"油多、盐多，这明显是猪食", weekend:true},
   	{name:"打拳皇", good:"上下左右ABCD，遇到的都是菜逼", bad:"被虐的惨不忍睹，不忍直视", weekend: true},
   	{name:"玩手游", good:"信号让你感觉是在飞", bad:"别白费力气了，你就是举过头顶也他妈没信号", weekend: true},
   	{name:"打扫屋内卫生", good:"打扫完成你的心情会很好", bad:"放弃吧，猪圈一般", weekend: true},
   	{name:"上淘宝", good:"看到合心意的东西，物美价廉", bad:"这个月连粥都喝不起了", weekend:true},
   	{name:"下单", good:"这宝贝完全跟描述的一样动人", bad:"收到之后发现是个残次品", weekend:true},
   	{name:"和男票滚床单", good:"各种高潮", bad:"有可能发生口角", weekend:true}
   	];

var specials = [
	{date:20140214, type:'bad', name:'待在男（女）友身边', description:'脱团火葬场，入团保平安。'}
];

var tools = ["Eclipse写程序", "MSOffice写文档", "记事本写程序", "Windows8", "Linux", "MacOS", "IE", "Android设备", "iOS设备"];

var varNames = ["jieguo", "huodong", "pay", "expire", "zhangdan", "every", "free", "i1", "a", "virtual", "ad", "spider", "mima", "pass", "ui"];

var drinks = ["水","茶","红茶","绿茶","咖啡","奶茶","可乐","鲜奶","豆奶","果汁","果味汽水","苏打水","运动饮料","酸奶","酒"];

function getTodayString() {
	return "今天是" + today.getFullYear() + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日 星期" + weeks[today.getDay()];
}

function star(num) {
	var result = "";
	var i = 0;
	while (i < num) {
		result += "★";
		i++;
	}
	while(i < 5) {
		result += "☆";
		i++;
	}
	return result;
} 

// 生成今日运势
function pickTodaysLuck(activities) {
  var _activities = filter(activities);
    
	var numGood = random(iday, 98) % 2 + 2;
	var numBad = random(iday, 87) % 2 + 2;
	var eventArr = pickRandomActivity(_activities, numGood + numBad);
	
	var specialSize = pickSpecials();
	
	for (var i = 0; i < numGood; i++) {
		addToGood(eventArr[i]);
	}
	
	for (var i = 0; i < numBad; i++) {
		addToBad(eventArr[numGood + i]);
	}
}

// 去掉一些不合今日的事件
function filter(activities) {
    var result = [];
    
    // 周末的话，只留下 weekend = true 的事件
    if (isWeekend()) {
        
        for (var i = 0; i < activities.length; i++) {
            if (activities[i].weekend) {
                result.push(activities[i]);
            }
        }
        
        return result;
    }
    
    return activities;
}

function isWeekend() {
    return today.getDay() == 0 || today.getDay() == 6;
}

// 添加预定义事件
function pickSpecials() {
	var specialSize = [0,0];
	
	for (var i = 0; i < specials.length; i++) {
		var special = specials[i];
		
		if (iday == special.date) {
			if (special.type == 'good') {
				specialSize[0]++;
				addToGood({name: special.name, good: special.description});
			} else {
				specialSize[1]++;
				addToBad({name: special.name, bad: special.description});
			}
		}
	}
	
	return specialSize;
}

// 从 activities 中随机挑选 size 个
function pickRandomActivity(activities, size) {
	var picked_events = pickRandom(activities, size);
	
	for (var i = 0; i < picked_events.length; i++) {
		picked_events[i] = parse(picked_events[i]);
	}
	
	return picked_events;
}

// 从数组中随机挑选 size 个
function pickRandom(array, size) {
	var result = [];
	
	for (var i = 0; i < array.length; i++) {
		result.push(array[i]);
	}
	
	for (var j = 0; j < array.length - size; j++) {
		var index = random(iday, j) % result.length;
		result.splice(index, 1);
	}
	
	return result;
}

// 解析占位符并替换成随机内容
function parse(event) {
	var result = {name: event.name, good: event.good, bad: event.bad};  // clone
	
	if (result.name.indexOf('%v') != -1) {
		result.name = result.name.replace('%v', varNames[random(iday, 12) % varNames.length]);
	}
	
	if (result.name.indexOf('%t') != -1) {
		result.name = result.name.replace('%t', tools[random(iday, 11) % tools.length]);
	}
	
	if (result.name.indexOf('%l') != -1) {
		result.name = result.name.replace('%l', (random(iday, 12) % 247 + 30).toString());
	}
	
	return result;
}

// 添加到“宜”
function addToGood(event) {
	$('.good .content ul').append('<li><div class="name">' + event.name + '</div><div class="description">' + event.good + '</div></li>');
}

// 添加到“不宜”
function addToBad(event) {
	$('.bad .content ul').append('<li><div class="name">' + event.name + '</div><div class="description">' + event.bad + '</div></li>');
}

$(function(){
	$('.date').html(getTodayString());
	$('.drink_value').html(pickRandom(drinks,2).join('，'));
	$('.goddes_value').html(star(random(iday, 6) % 5 + 1));
	var info = Android.getInfo();
	switch (info) {
	case "000":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("写程序，BUG 最少。");
		pickTodaysLuck(programmer_m);
		break;
	case "001":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("写程序，BUG 最少。");
		pickTodaysLuck(programmer_w);
		break;
	case "010":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("嗑瓜子，仪器出问题最少。");
		pickTodaysLuck(oilman_m);
		break;
	case "011":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("嗑瓜子，仪器出问题最少。");
		pickTodaysLuck(oilman_w);
		break;
	case "020":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("上网，之前的工作出错最少。");
		pickTodaysLuck(finance_m);
		break;
	case "021":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("上网，之前的工作出错最少。");
		pickTodaysLuck(finance_w);
		break;
	case "030":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("做设计，再修改该的可能性最少。");
		pickTodaysLuck(designer_m);
		break;
	case "031":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("做设计，再修改该的可能性最少。");
		pickTodaysLuck(designer_w);
		break;
	case "040":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("看病历，今天看病的人不会太多。");
		pickTodaysLuck(doctor_m);
		break;
	case "041":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("看病历，今天看病的人不会太多。");
		pickTodaysLuck(doctor_w);
		break;
	case "050":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("上招聘网，很快就找到符合标准的人才。");
		pickTodaysLuck(hr_m);
		break;
	case "051":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("上招聘网，很快就找到符合标准的人才。");
		pickTodaysLuck(hr_w);
		break;
	case "060":
		$('.ta_value').html("女神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("做文案，思路泉涌。");
		pickTodaysLuck(planner_m);
		break;
	case "061":
		$('.ta_value').html("男神");
		$('.direction_value').html(directions[random(iday, 2) % directions.length]);
		$('.better_value').html("做文案，思路泉涌。");
		pickTodaysLuck(planner_w);
		break;
	default:
		$('.ta_value').html("女神");
		pickTodaysLuck(programmer_m);
		break;
	}
});