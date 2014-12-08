package sid.utils;

import java.util.ArrayList;
import java.util.List;

import sid.modle.Constellation;
import android.graphics.Color;

public class AppConstant {
	public static final String GE_UID = "2195d3568afd168dBpgFmcnKFkq3wXvZ5X/+y3hMDO7D9S6EzeK5I5jTvwQ7W5mM0w";
	public static final String GE_PID = "网易";
	/** 设置文字颜色*/
	public static final int TEXT_COLOR = Color.rgb(62, 184, 248);
	/** 设置文字颜色*/
	public static final int TEXT_BG_DEFAULT_COLOR = Color.rgb(255, 255, 255);
	/** 设置文字颜色*/
	public static final int TEXT_BG_ONCLICK_COLOR = Color.rgb(27, 68, 254);
	/** 设置文字的字体大小*/
	public static final float CG_USER_TEXT_SIZE = 15f;
	/** 设置数据库名称*/
	public static final String DATABASE = "lover_sid_db";
	/** 设置数据库版本*/
	public static final int VERSION = 1;
	public static final int DATE_PICKER_ID = 1;
	public static final int PHNOE_PICKER_ID = 2;
	public static final int DES_PICKER_ID = 3;
	public static final int NICK_PICKER_ID = 4;
	public static final int TIME_PICKER_ID = 5;
	public static final int QUESTION_PICKER_ID = 6;
	public static final int ANSWER_PICKER_ID = 7;
	public static final int PASSWORD_PICKER_ID = 8;
	public static final int RESET_PICKER_ID = 9;
	public static final int PERIODS_PICKER_ID = 10;
	public static final int PERIOD_PICKER_ID = 11;
	/** 设置人类型*/
	public static final int LOVER = 101;
	/** 设置intent类型*/
	public static final int NOTE_FAVORITE = 801;
	/** 设置intent类型*/
	public static final int NOTE_EXPERIENCE = 802;
	/** 设置intent类型*/
	public static final int NOTE_LOVERSTORY = 803;
	/** 设置intent类型*/
	public static final int NOTE_PERIOD = 804;
	/** 设置生理期注意事项*/
	public static final String NOTE_PERIOD_TEXT = "一般注意事项\n\n女孩生理期是3天到7天左右，这期间从女孩的子宫通过阴道排出一定的血液，这些血液是很干净的。这期间要注意不能太劳累，不可吃生冷食物，心情要保持舒畅，更要注意卫生，保持阴部清洁。\n\n1、生理期期间尽量不要洗头：洗完头一定要立即吹干。生理期期间，可中午洗头。\n\n2、生理期期间要注意保暖：也就是少吹冷气啰，冷气开着，在冷气房里还是要多穿件衣服。\n\n3、忌喝冷饮：这已经是很基本常识了，千万不要违背，不要拿自己的美丽开玩笑，喝冰水会让污血无法顺利排出，留在身体里对身体绝对有害。\n试试看以下饮料，可以让生理期更舒适，桂圆红枣茶、养生茶、贞味丹蔘、红糖姜水、黄耆枸杞茶、玫瑰花茶（尤其如果会经痛，可以喝点玫瑰花茶）。\n\n4、吃点甜食：生理期间吃巧克力、蛋糕，因为生理期吃一点甜食可以帮助污血代谢。\n\n5、拒绝疲劳：充份休息很重要，过于劳累会导致经期延长或是失血过多。\n\n6、不要太情绪化：据说女孩子生理期时很自然地情绪就会不好，但是也不要纵容自己乱发脾气，因为发脾气会对扰乱经期，也很伤肝脏喔。\n\n7、忌盆浴：因为生理期子宫口比较张开，同样地也较为容易受到污染，所以洗澡最好采淋浴。\n\n8、生理期宜食：生理期刚来几天可以多吃点麻油猪肝，帮助废血排出。后几天可以吃麻油腰花。此外麻油炒蛋加九层塔也是不错的生理期食品。红豆汤、桂圆汤、八宝粥都是好点心喔，还有酒酿煮蛋也很好。生理期后期，可以考虑喝一点生化汤（经期喝的，不是坐月子喝的生化汤，问中药房就知道），可以让身体脏血彻底排乾净。\n\n身体里脏血若不排出干净，长期累积很容易造成子宫内膜炎、肝病、红斑性狼疮等疾病。为什么女生比男生不容易得肝病呢？据说就是因为每月的经血将身体中的毒素泄乾净了，所以比较不容易得肝病。\n\n9、生理期后的调养：吃四物汤啰！在经期来时喝四物汤是错误的，要等到脏血都排除后再喝四物汤补血才有效果，否则就白补了。四物汤煮时可以放点排骨或鸡肉，最好加点米酒。\n\n10、脸部清洁工作要做好：生理期间因为毛孔比较张开，很容易长痘痘，脸部清洁工作要做好，生理期间洗头因为造成血液循环不好，也容易长痘与粉刺。\n\n其它8点注意事项\n\n除了传统意义上的生理期不能做的10件事之外，还要注意最新补充的8点注意事项：\n1、捶腰\n\n腰酸腿胀时，我们通过捶打酸胀的肌肉来缓解不适，同样，不少女性在经期也会习惯性地捶打腰部来缓解腰部酸胀。\n但这么做，却犯了错。妇科专家指出，经期腰部酸胀是盆腔充血引起的，此时捶打腰部会导致盆腔更加充血，反而加剧酸胀感。另外，经期捶腰还不利于子宫内膜剥落后创面的修复愈合，导致流血增多，经期延长。\n\n2、体检\n\n经期除了不适宜做妇科检查和尿检，同样不适宜做血检和心电图等检查项目。\n因为此时受荷尔蒙分泌的影响，难以得到真实数据。\n\n3、拔牙\n\n恐怕很少有牙医在拔牙前，会询问你是否在经期，但你自己一定要知道，不能在经期拔牙！\n否则，不仅拔牙时出血量增多，拔牙后嘴里也会长时间留有血腥味，影响食欲，导致经期营养不良。这是因为月经期间，子宫内膜释放出较多的组织激活物质，将血液中的纤维蛋白溶解酶原激活为具有抗凝血作用的纤维蛋白溶解酶，同时体内的血小板数目也减少，因此身体凝血能力降低，止血时间延长。\n\n4、用沐浴液清洁阴部\n\n经期阴部容易产生异味，尤其在夏季，但在洗澡时顺便用沐浴液清洁阴部，或用热水反复清洗阴部是不够健康的，反而容易引发阴部感染，导致瘙痒病症。\n因为平日女性阴道内是略酸性环境，能抑制细菌生长，但行经期间阴道会偏碱性，对细菌的抵抗力降低，易受感染，如果不使用专业的阴道清洁液或用热水反复清洗更会导致碱性增加。因此，清洗阴部需要选择专业的阴部清洗液，尤其在经期。\n\n5、做爱\n\n说实话，这确实是对意志力的挑战，尤其在经期的最后一两天。\n一方面，这两天的经血量明显减少，常常被情侣们主观忽略；另一方面，此时女性体内荷尔蒙增多，性欲增强，旺盛的荷尔蒙也同时刺激着男方的激情。但由于此时宫颈仍处于微张状态，子宫内膜也没有完全修复，留有创面，因此容易被细菌侵入。另外，受爱的运动刺激而游走到别处的子宫内膜碎片可能埋下子宫内膜异位的隐患。\n\n6、饮酒\n\n同样是受体内荷尔蒙分泌影响，经期女性体内的解酒酶减少，因此饮酒易醉。\n更严重的是，为了制造出分解酶来帮助分解酒精，肝脏负担明显加重，因此此期间饮酒会对肝脏造成比平日严重的伤害，引发肝脏机能障碍的可能性增大。\n\n7、k歌\n\n经期女性，声带的毛细血管也充血，管壁变得较为脆弱。\n此时长时间或高声K歌，可能由于声带紧张并高速振动而导致声带毛细血管破裂，声音沙哑，甚至可能对声带造成永久性伤害，如嗓音变低或变粗等。专业医师特别提醒，女性从月经来潮前两天开始就应该注意不要长时间或高声唱歌。\n\n8、吃油炸食品\n\n油炸食品也是经期女性的一大禁忌。\n因为受体内分泌的黄体酮影响，经期女性皮脂分泌增多，皮肤油腻，同时毛细血管扩张，皮肤变得敏感。此时进食油炸食品，会增加肌肤负担，容易出现粉刺、痤疮、毛囊炎，还有黑眼圈。另外，由于经期脂肪和水的代谢减慢，此时吃油炸食品，脂肪还容易在体内囤积";
	/** 设置intent类型*/
	public static final int INTENT_DATE = 804;
	public static List<String> items = new ArrayList<String>();
	static{
		items.add("重复");
		items.add("铃声");
		items.add("振动");
		items.add("标签");
	}
	public static List<String> paths = new ArrayList<String>();
	static{
		paths.add("重复");
		paths.add("铃声");
		paths.add("振动");
		paths.add("标签");
	}
	public static final int WEEKS = 10;
	public static final int INTERVAL = 11;
	public static final int BELLS = 12;
	public static final int SHOCK = 13;
	public static final int SIGNS = 14;
	public static final int TITLE = 17;
	public static final int STATUS = 18;
	public static final int SHOCKONE = 20;
	public static final int SHOCKREP = 21;
	public static final int ButtonAlarm = 888;
	public static final String[] weeks = new String[] {"周日","周一","周二","周三","周四","周五","周六"};
	public static final String[] shocks = new String[] {"振动伴随响铃","响铃","振动"};
	/** 闹钟类型：0:起床闹钟；1：纪念日闹钟；2：短信提醒 **/
	public static final int[] signs = new int[] {0,1,2};
	public static final String[] status = new String[] {"开启提醒","关闭提醒"};
	public static final String strAlarmFolder = "/music/alarm";
    //这边的Long型数组中，第一个参数是开始振动前等待的时间，第二个参数是第一次振动的时间，第三个参数是第二次振动的时间，以此类推，随便定义多长的数组。但是采用这种方法，没有办法做到重复振动。
	public static final long[] vibrate = {0,100,200,300,400,500,600,700,800,900,1000,
   		 1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,
   		 2100,2200,2300,2400,2500,2600,2700,2800,2900,3000,
   		 3100,3200,3300,3400,3500,3600,3700,3800,3900,4000,
   		 4100,4200,4300,4400,4500,4600,4700,4800,4900,5000,
   		 5100,5200,5300,5400,5500,5600,5700,5800,5900,6000,
   		 6100,6200,6300,6400,6500,6600,6700,6800,6900,7000,};
	
	/** 设置menu的关于按钮值*/
	public static final int ABOUT = 0;
	/** 设置menu的退出按钮值*/
	public static final int EXIT = 1;
	/** 设置关于文字的字体大小*/
	public static final float ABOUT_TEXT_SIZE = 20f;
	/** 便利贴表名*/
	public static final String SETTING = "setting";
	/** 便利贴表名*/
	public static final String NOTE = "note";
	/** 闹钟表名*/
	public static final String CLOCK = "clock";
	/** 人物表名*/
	public static final String PERSON = "person";
	/** 十二星座表名*/
	public static final String CONSTELLATION = "constellation";
	public static List<Constellation> constellations = new ArrayList<Constellation>();
	static{
		constellations.add(new Constellation(1, "白羊座（ARIES）", 321, 419, "\n&#160;&#160;&#160; 3月21日～4月19日 \n\n&#160;&#160;&#160;星座特征 \n\n&#160;&#160;&#160;精力旺盛、活力充沛的星座。性格与爱情多数“横冲直撞”，但纯真的个性里没有杀伤力，所以不用太担心。白羊座是十二星座中的第一个星座，代表着初生的原始灵魂和感觉。基本上，这个星座出生的人，可以视他们为一个永远具备赤子之心的孩子，无论他的年龄是多少！他们充满了强烈的好奇心、坚强的意志力，不服输和冒险犯难、创新求变的精神；往往将第一视为理所当然，不喜欢落在别人的后面。当他们面对竞争压力时，战斗力十足而且洋溢着热情活力，是行动派的人物。 \n\n&#160;&#160;&#160;自我意识和主观意识很强，充满自信而且固执；不会等待机会从天而降，而会积极的争取或制造，无畏艰难和困苦。虽然有时会显得冲动，但基本上还是会保持理智和果决，是个适合面对竞争压力、热情且永远天真未泯的人。 \n\n&#160;&#160;&#160;性格代表词语：我要 \n\n&#160;&#160;&#160;守护星：火星（象征能量与精力） \n\n&#160;&#160;&#160;守护神：希腊－阿瑞斯 罗马－马斯"));
		constellations.add(new Constellation(2, "金牛座（TAURUS）", 420, 520, "\n&#160;&#160;&#160; 4月20日～5月20日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;慢条斯理的星座。凡事总是考虑后再过滤，属于大器晚成型，情思也比较晚开。但他们有超人的稳定性，一旦下赌注，就有把握赢。\n\n&#160;&#160;&#160;金牛座是黄道的第二个星座，是“土象星座”的第一个星座，故也称“土象的婴孩”。如同这个名称所示，他是一只不折不扣的“牛”；而且是一头有“金”（物欲）特性，占有欲很强的牛。\n\n&#160;&#160;&#160;金牛座的人似乎天生就有忧郁和压抑的性格。当这些累积到顶点时，就会如同火山一般的爆开。他们在十二星座中算是工作最勤勉，刻苦耐劳、坚忍不拔的；耐心、耐力、韧性是其特性。他们相信拥有爱情、美丽与富有的喜悦，是生命存在的证明，也是他信仰的真理，为着这个目的；他们会选择最安全、确实的途径（通常是长期的酝酿和深思熟虑的结论），一旦下定决心，没有人可以改变它。\n\n&#160;&#160;&#160;他们忠诚、真心、善解人意、实际、不浮夸、率真、负责，凡事讲求规则及合理性。喜欢新的理念并会花时间去接触、证明，是个自我要求完美的人；同时他们对物质和美的生产力方面，也是超人一等。在艺术创作的领域中，不是艺术家就是优秀的鉴赏者。"));
		constellations.add(new Constellation(3, "双子座（GEMINI）", 521, 621, "\n&#160;&#160;&#160; 5月21日～6月21日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;变化速度快如风的星座。双子的双重性格常搞的别人和自己头痛万分，对于事业与爱情，如果肯多花点心思经营，应该会是很好的，但他们实在是太机灵了。\n\n&#160;&#160;&#160;基本上，双子座人的意志一直都是一体两面的——积极与消极，动与静、明与暗，相互消长，共荣共存的。通常很多才多艺，也可同时处理很多事情，有些则会表现出明显的两种或多种人格，这种多变的特性，往往令人难以捉摸。他们相当具有灵性、聪明、心智活跃敏锐，喜欢忙碌和追求新的概念及作事的方法，有活力、口才一流、活力充沛、胸怀大志、人缘很好，并且都有语言天份。对事物的思考很快，改变主意也比一般人快。由于水星的影响，使双子座的人通常无法控制自己的思考力，很容易导致精神衰弱。\n\n&#160;&#160;&#160;双子座有着双倍于别人的力量、思考力，却也需要比别人多一倍的时间去恢复。所以，双子座是一个善良与邪恶，快乐与忧郁，温柔与残暴兼具的复杂星座。"));
		constellations.add(new Constellation(4, "巨蟹座（CANCER）", 622, 722, "\n&#160;&#160;&#160; 6月22日～7月22日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;非常需要爱与安定的星座。爱猜疑的个性，使他们在人生旅途上处处显得缺乏安全感。但是带着母爱光辉的巨蟹，为了所爱倒是心甘情愿的付出。\n\n&#160;&#160;&#160;巨蟹座的人天生具有旺盛的精力和敏锐的感觉，道德意识很强烈，对欲望的追求也总能适度的停止。有精辟的洞察能力，自尊心也很强，同时也生性慷慨、感情丰富，乐意帮助有需要的人，并喜欢被需要与被保护的感觉。大部份巨蟹座的人都比较内向、羞怯，虽然他们常用一种很表面的夸张方式来表达，但基本上他们是缺乏自信的，也不太能适应新的环境。虽然对新的事物都很感兴趣，但真实却是很传统、恋旧的，似乎看来有些双重个性；如果换一个角度来看，他们只是对情绪的感受力特别强吧！\n\n&#160;&#160;&#160;巨蟹座是十二星座中最具有母性的星座，男性亦然。和善、体贴、宽容不记仇，对家人与好朋友非常忠诚。记忆力很好，求知欲很强，顺从性强，想象力也极丰富。\n\n&#160;&#160;&#160;他们的守护星是月亮，所以只要观察它就可揣测出他们的心情变化；他们喜欢探索别人的秘密，却把自己隐藏的很好，并且从不放弃他所要的东西。"));
		constellations.add(new Constellation(5, "狮子座（LEO）", 723, 822, "\n&#160;&#160;&#160; 7月23日～8月22日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;讲究气派华丽的星座。狮子是森林之王，理所当然喜欢呼朋引伴，有些耐不住寂寞。他们有冲劲，虽然粗枝大叶，但为人讲义气，也蛮有人缘。\n\n&#160;&#160;&#160;在十二星座中，狮子座是最具有权威感与支配能力的星座。通常有一种贵族气息或是王者风范。受人尊重，做事相当独立，知道如何运用能力和权术以达到目的。\n\n&#160;&#160;&#160;本质是阳刚、专制、具有太阳般的活力、宽宏大量、乐观、海派、光明磊落、不拘小节、心胸开阔。不过也会有顽固、傲慢、独裁的一面。同时，他们天生怀抱着崇高的理想，能够全力以赴、发挥旺盛的生命力为周遭的人、为弱者或正义而战。对弱者有慈悲心及同情心，对自己很有自信，擅长组织事务，喜欢有秩序；能够发挥创造的才华，使成果具有建设性、原创性，是个行动派。\n\n&#160;&#160;&#160;有时也相当浪漫，喜欢美丽的事并爱炫耀、豪华及被人围绕与赞美。狮子座的人热爱生命、好享乐、勇敢、坚持原则及理念。个性温暖、友善、体贴、外向、对人慷慨大方，很容易交朋友，人缘当然也很不错。"));
		constellations.add(new Constellation(6, "处女座（VIRGO）", 823, 922, "\n&#160;&#160;&#160; 8月23日～9月22日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;有点挑剔又追求完美的星座。缺乏信心的个性，常在潜意识里责怪自己不够美好；虽然难免会使心情沉重，但天生的优点就是放得开，不会就此一蹶不振。\n\n&#160;&#160;&#160;处女座的特色是有丰富的知性，做事一丝不苟，有旺盛的批判精神（那是因为他们总希望世事能和他们的主观标准相同），是个完美主义者，极度的厌恶虚伪与不正当的事。无论年纪大小，都保有一颗赤子之心，充满了对过去的回忆及对未来的梦想。通常他们也很实际，总是可以使爱幻想和实际的性格共存且并荣。\n\n&#160;&#160;&#160;做事周到、细心、谨慎而有条理，并非常理性，甚至冷酷。有特殊的评论能力，喜欢把事情一点一点的分析、批判。强调完整性，不喜欢半途而废；对任何事都有一套详细的规划，然后一步步的实施并完全掌握。做什么事都很投入，而且好学、好奇、求知欲旺盛。他们对自己的要求很严格，从不妥协、让步，是个优秀的幕僚人才及工作狂。\n\n&#160;&#160;&#160;外表安静沉默，对外力的冲突，总是采取逃避的方式，那是因为他们天生较内向、胆怯和孤独的缘故；但只要自己能够确定时，便会变得比较大胆。\n\n&#160;&#160;&#160;性格代表字汇：我分析\n\n&#160;&#160;&#160;守护星：水星（象征知性与工作）\n\n&#160;&#160;&#160;守护神：希腊－汉密斯 罗马－墨格利"));
		constellations.add(new Constellation(7, "天秤座（LIBRA）", 923, 1022, "\n&#160;&#160;&#160; 9月23日～10月22日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;爱美又怕空虚的星座。凭着天生的外交本领，周旋在各色人物之间；但有时也因为太顾虑面面俱到，而搞的自己吃力不讨好，脑筋常在那儿转来转去，当心神经衰弱。\n\n&#160;&#160;&#160;天秤座的人爱好美与和谐，也相当仁慈并富有同情心，天性善良温和、体贴、沉着。由于受到金星的影响（这点和金牛座相同。它掌管的是爱、美、婚姻、金钱的丰盛收成），有着优秀的理解能力和艺术鉴赏力，但往往会把任何事物都当做艺术和游戏，以这一体两面的方式来表现。\n\n&#160;&#160;&#160;天秤座也是俊男美女最多的一个星座，也具有创作的天份，人缘及口才都很好。他们看待事物较客观，常为人设身处地着想，通常也较外向，感情丰富，视爱情为唯一的一切。属于人群中的人，但有时也会显得多愁善感，但这也属于他们的魅力之一。同时他们也是最能保守秘密的人，就像他们可以把心中澎湃的热情隐藏的很好一样。\n\n&#160;&#160;&#160;天秤座的人天生具有理想主义和现实主义，性格极端矛盾、交杂反复；他们是和平的使者也是战士，亦是个兼具感性、公平公正及贵族气息的人。\n\n&#160;&#160;&#160;性格代表词汇：我想\n\n&#160;&#160;&#160;守护星：金星（象征爱与美的结合）\n\n&#160;&#160;&#160;守护神：希腊－阿弗萝蒂 罗马－维纳斯"));
		constellations.add(new Constellation(8, "天蝎座（SCORPIO）", 1023, 1121, "\n&#160;&#160;&#160; 10月23日～11月21日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;神秘诡谲、令人费疑猜的星座。他们可以很执着，也可以很破坏；在爱情的国度里黑白分明，没有灰色地带。他们对于自己的目标相当清楚，一旦确立就往前冲。\n\n&#160;&#160;&#160;天蝎座的人有着强烈的第六感、神秘的探视能力及吸引力，做事常凭直觉；虽然有着敏锐的观察力，但往往仍靠感觉来决定一切。\n\n&#160;&#160;&#160;天蝎座个性强悍而不妥协，也非常好胜，这是一种自我要求的自我超越，以不断填补内心深处的欲望。也由于如此，蝎族的人在心中总订有一个目标，非常有毅力，以不屈不挠的斗志和战斗力，深思熟虑的朝目标前进。也由于是水象星座的缘故，在情感上亦属多愁善感的敏锐型，但却以自我为中心，对别人的观点亦完全不予理会。通常他们是深情而且多情的，虽然表面上看起来很平静、温文儒雅、沉默寡言，但内心却是波涛汹涌。他们在决定行动时会表现的大胆积极，属于敢爱敢恨的类型。\n\n&#160;&#160;&#160;总之，天蝎座是一个有强烈责任感、韧性强、有概念、会组织（条理化）、意志力强、支配欲强烈，对于生命的奥秘有独特见解的本能，并且永远有着充沛精力的微妙复杂——“混合体”。\n\n&#160;&#160;&#160;性格代表词语：我渴望\n\n&#160;&#160;&#160;守护星：冥王星（象征转变）\n\n&#160;&#160;&#160;守护神：希腊－普鲁特 罗马－黑底斯"));
		constellations.add(new Constellation(9, "射手座（SAGITTARIUS）", 1122, 1221, "\n&#160;&#160;&#160; 11月22日～12月21日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;自由浪漫也滥情的星座。不爱受约束的个性使他们很怕被捆绑，多情的天性也使他们四处寻求猎物；性情天真，常会伤了人也不自觉，爱他们就由他们去吧！\n\n&#160;&#160;&#160;射手座的人崇尚自由、无拘无束及追求速度的感觉，生性乐观、热情，是个享乐主义派。射手座的守护星是希腊神话中的宙斯－宇宙的主宰和全知全能的众神之王。所以是个神圣的完美主义者，有阳刚的气息、宽大体贴的精神，重视公理与正义的伸张。\n\n&#160;&#160;&#160;他们幽默、刚直率真、对人生的看法富含哲学性，也希望能将自身所散发的火热生命力及快感，感染到别人，所以人缘通常都很好。他们外向、健谈、喜欢新的经验与尝试，尤其是运动及旅行。是个永远无法被束缚、不肯妥协、同时又具备人性与野性、精力充沛且活动力强，有着远大的理想，任何时地都不会放弃希望和理想。他们始终在追求一个能完全属于自己的生活环境，但可能是因他们有着豁达的人生观，所以有时常会乐观得太过~~“一厢情愿”了。\n\n&#160;&#160;&#160;性格代表词语：我追求\n\n&#160;&#160;&#160;守护星：木星（象征狂热与力量）\n\n&#160;&#160;&#160;守护神：希腊－宙斯 罗马－朱彼得"));
		constellations.add(new Constellation(10, "摩羯座（CAPRICORN）", 1222, 119, "\n&#160;&#160;&#160; 12月22日～1月19日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;严谨刻板、稳重老成的星座。虽然一向给人呆板的印象，但是呆板的人普遍说来都不太耍花样；不管是在事业或爱情上，他们也都以这份特殊气质获胜！\n\n&#160;&#160;&#160;摩羯座就像是只走在高山绝壁的山羊一样稳健踏实，会小心翼翼渡过困厄的处境。通常都很健壮，有过人的耐力、意志坚决、有时间观念、有责任感、重视权威和名声，对领导统御很有一套，自成一格，另外组织能力也不错。\n\n&#160;&#160;&#160;和其它土象星座一样，是属于较内向，略带忧郁、内省、孤独、保守、怀旧、消极、没有安全感，也欠缺幽默感，常会装出高高在上或是严厉的姿态，以掩饰自己内在的脆弱。\n\n&#160;&#160;&#160;通常他们也绝少是天才型，但是却心怀大志，经过重重的历炼，到中年期才会渐渐拥有声名和成功。一方面是因为他们有安定的向上心和坚强的毅力，加上擅长知识和经验的累积，如此才一点一滴的达成目标的。虽然有时为了这成功的目标，也会用一些残忍无情的策略，但摩羯座还算是有正义感的。他们擅于外交、好动、活力充沛、目标确定；重视现实利益及物质保障，具有宗教或神秘学上的理解能力及人文科学的逻辑概念，是属于大器晚成的类型。\n\n&#160;&#160;&#160;性格代表词语：我作\n\n&#160;&#160;&#160;守护星：土星（象征绝对的理性）\n\n&#160;&#160;&#160;守护神：希腊－克罗诺斯 罗马－萨登"));
		constellations.add(new Constellation(11, "水瓶座（AQUARIUS）", 120, 218, "\n&#160;&#160;&#160; 1月20日～2月18日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;思想超前、理性自重的星座。一样的不爱受约束、一样的博爱，但他们还是不同于射手座；他们较着重于精神层次的提升，是很好的启发对象。\n\n&#160;&#160;&#160;水瓶座常被称为“天才星座”或“未来星座”。因为它的守护星是天王星，而希腊神话中上通天文、下知地理，并有预知未来能力的智能大神——乌拉诺斯，是它的守护神。所以他们具有前瞻性、有独创性、聪慧、富理性，喜欢追求新的事物及生活方式。\n\n&#160;&#160;&#160;他们的心胸宽大、爱好和平，主张人人平等、无分贵贱贫富，不但尊重个人自由，也乐于助人、热爱生命，是个典型的理想主义和人道主义者；他们深信世上自有公理，所以常有改革（或革命）的精神。\n\n&#160;&#160;&#160;另外，他们也很重视理论和知识，有优秀的推理力和创造力，客观、冷静，善于思考，思想博爱，讲求科学、逻辑和概念，价值观很强。是一个对超能力、超自然现象会积极证明，人缘及辩才均佳，忠于自己信念，又令人难以捉摸的星座。水瓶座虽是个理想主义者，但他们一旦碰上爱情，就会变的非常实际。\n\n&#160;&#160;&#160;性格代表词语：我了解\n\n&#160;&#160;&#160;守护星：天王星（象征智能及变量）\n\n&#160;&#160;&#160;守护神：希腊－乌拉诺斯 罗马－乌拉诺斯"));
		constellations.add(new Constellation(12, "双鱼座（PISCES）", 219, 320, "\n&#160;&#160;&#160; 2月19日～3月20日\n\n&#160;&#160;&#160;星座特征\n\n&#160;&#160;&#160;多愁敏感，爱作梦、幻想的星座。天生多情，使他们常为情字挣扎，情绪的波动起伏也跟情脱不了关系；他们生性柔弱，很喜欢奉献，也不会随意伤人。\n\n&#160;&#160;&#160;双鱼座是古老轮回的结束，这种古老轮回后的灵魂，是一种透澈。也许正因如此，他们总深陷在灵和欲之间，退缩在一种自创的梦幻之境里。他们爱作梦，也无时不在幻想，也常将这种情结搬到现实环境中，而显得有些不切实际，但他们是善良的，有绝对舍己助人的牺牲奉献精神；他们是敏感、仁慈、和善、宽厚、与世无争、温柔、多愁善感的纯情主义者，也是十二星座中最“多情”的一个。\n\n&#160;&#160;&#160;双鱼是个古老复杂的星座，包含了太多的情绪，所以在情绪方面起伏非常的大，矛盾、敏锐的感性、知性、诗情和纤细的触觉，种种冲击之下便产生了无与伦比的艺术天才。例如在我们所研究的古典音乐大之中，双鱼座便是十二星座中最多的。也许，这也是他们另一种沉醉的表现。\n\n&#160;&#160;&#160;总之这是一个充满神性、魔性、理解力，观察力强却又忧柔寡断、缺乏自信、神经质的（如果是女人则更是泪水做成的，女人中的女人）、自制力不强、又善变的像谜一般的星座。双鱼座的星座象征，正是两只鱼各往相反的方向游，一只向上，一只向下；没有什么比这幅画面，更能正确形容双鱼座的复杂性格了。\n\n&#160;&#160;&#160;性格代表字汇：我了解\n\n&#160;&#160;&#160;守护星：海王星（象征高超的幻想力）\n\n&#160;&#160;&#160;守护神：希腊－波西顿"));
	}

}
