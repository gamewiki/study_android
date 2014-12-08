package sid.utils;

import java.util.ArrayList;
import java.util.List;

import sid.modle.DayWeight;
import sid.modle.Earth;
import sid.modle.HourWeight;
import sid.modle.MonthWeight;
import sid.modle.PersonDestiny;
import sid.modle.Sky;
import sid.modle.YearWeight;
import android.graphics.Color;

public class AppConstant {
	/** 设置文字颜色*/
	public static final int TEXT_COLOR = Color.rgb(62, 184, 248);
	/** 设置称骨文字的字体大小*/
	public static final float CG_USER_TEXT_SIZE = 15f;
	/** 设置数据库版本*/
	public static final int VERSION = 1;
	public static List<Sky> skys = new ArrayList<Sky>();
	static{
		skys.add(new Sky(1,"甲"));
		skys.add(new Sky(2,"乙"));
		skys.add(new Sky(3,"丙"));
		skys.add(new Sky(4,"丁"));
		skys.add(new Sky(5,"戊"));
		skys.add(new Sky(6,"己"));
		skys.add(new Sky(7,"庚"));
		skys.add(new Sky(8,"辛"));
		skys.add(new Sky(9,"壬"));
		skys.add(new Sky(0,"癸"));
	}
	public static List<Earth> earths = new ArrayList<Earth>();
	static{
		earths.add(new Earth(1,"子","鼠"));
		earths.add(new Earth(2,"丑","牛"));
		earths.add(new Earth(3,"寅","虎"));
		earths.add(new Earth(4,"卯","兔"));
		earths.add(new Earth(5,"辰","龙"));
		earths.add(new Earth(6,"巳","蛇"));
		earths.add(new Earth(7,"午","马"));
		earths.add(new Earth(8,"未","羊"));
		earths.add(new Earth(9,"申","猴"));
		earths.add(new Earth(10,"酉","鸡"));
		earths.add(new Earth(11,"戌","狗"));
		earths.add(new Earth(0,"亥","猪"));
	}
	public static List<YearWeight> years = new ArrayList<YearWeight>();
	static{
		years.add(new YearWeight(1101,"甲子",12));
		years.add(new YearWeight(1301,"丙子",16));
		years.add(new YearWeight(1501,"戊子",15));
		years.add(new YearWeight(1701,"庚子",7));
		years.add(new YearWeight(1901,"壬子",5));

		years.add(new YearWeight(1202,"乙丑",9));
		years.add(new YearWeight(1402,"丁丑",8));
		years.add(new YearWeight(1602,"己丑",7));
		years.add(new YearWeight(1802,"辛丑",7));
		years.add(new YearWeight(1002,"癸丑",7));

		years.add(new YearWeight(1303,"丙寅",6));
		years.add(new YearWeight(1503,"戊寅",8));
		years.add(new YearWeight(1703,"庚寅",9));
		years.add(new YearWeight(1903,"壬寅",9));
		years.add(new YearWeight(1103,"甲寅",12));

		years.add(new YearWeight(1404,"丁卯",7));
		years.add(new YearWeight(1604,"己卯",19));
		years.add(new YearWeight(1804,"辛卯",12));
		years.add(new YearWeight(1004,"癸卯",12));
		years.add(new YearWeight(1204,"乙卯",8));

		years.add(new YearWeight(1505,"戊辰",12));
		years.add(new YearWeight(1705,"庚辰",12));
		years.add(new YearWeight(1905,"壬辰",10));
		years.add(new YearWeight(1105,"甲辰",8));
		years.add(new YearWeight(1305,"丙辰",8));

		years.add(new YearWeight(1606,"己巳",5));
		years.add(new YearWeight(1806,"辛已",6));
		years.add(new YearWeight(1006,"癸已",7));
		years.add(new YearWeight(1206,"乙已",7));
		years.add(new YearWeight(1406,"丁已",6));

		years.add(new YearWeight(1707,"庚午",9));
		years.add(new YearWeight(1907,"壬午",8));
		years.add(new YearWeight(1107,"甲午",15));
		years.add(new YearWeight(1307,"丙午",13));
		years.add(new YearWeight(1507,"戊午",19));

		years.add(new YearWeight(1808,"辛未",8));
		years.add(new YearWeight(1008,"癸未",7));
		years.add(new YearWeight(1208,"乙未",6));
		years.add(new YearWeight(1408,"丁未",5));
		years.add(new YearWeight(1608,"己未",6));

		years.add(new YearWeight(1909,"壬申",7));
		years.add(new YearWeight(1109,"甲申",5));
		years.add(new YearWeight(1309,"丙申",5));
		years.add(new YearWeight(1509,"戊申",14));
		years.add(new YearWeight(1709,"庚申",8));

		years.add(new YearWeight(1010,"癸酉",8));
		years.add(new YearWeight(1210,"乙酉",15));
		years.add(new YearWeight(1410,"丁酉",14));
		years.add(new YearWeight(1610,"己酉",5));
		years.add(new YearWeight(1810,"辛酉",16));

		years.add(new YearWeight(1111,"甲戌",15));
		years.add(new YearWeight(1311,"丙戌",6));
		years.add(new YearWeight(1511,"戊戌",14));
		years.add(new YearWeight(1711,"庚戌",9));
		years.add(new YearWeight(1911,"壬戌",10));

		years.add(new YearWeight(1200,"乙亥",9));
		years.add(new YearWeight(1400,"丁亥",16));
		years.add(new YearWeight(1600,"己亥",9));
		years.add(new YearWeight(1800,"辛亥",17));
		years.add(new YearWeight(1000,"癸亥",7));
	}
	public static List<MonthWeight> months = new ArrayList<MonthWeight>();
	static{
		months.add(new MonthWeight(1,"正月",6));
		months.add(new MonthWeight(2,"二月",7));
		months.add(new MonthWeight(3,"三月",18));
		months.add(new MonthWeight(4,"四月",9));
		months.add(new MonthWeight(5,"五月",5));
		months.add(new MonthWeight(6,"六月",16));
		months.add(new MonthWeight(7,"七月",9));
		months.add(new MonthWeight(8,"八月",15));
		months.add(new MonthWeight(9,"九月",18));
		months.add(new MonthWeight(10,"十月",8));
		months.add(new MonthWeight(11,"冬月",9));
		months.add(new MonthWeight(12,"腊月",5));
	}
	public static List<DayWeight> days = new ArrayList<DayWeight>();
	static{
		days.add(new DayWeight(1,"初一",5));
		days.add(new DayWeight(2,"初二",12));
		days.add(new DayWeight(3,"初三",8));
		days.add(new DayWeight(4,"初四",15));
		days.add(new DayWeight(5,"初五",16));
		days.add(new DayWeight(6,"初六",15));
		days.add(new DayWeight(7,"初七",8));
		days.add(new DayWeight(8,"初八",16));
		days.add(new DayWeight(9,"初九",8));
		days.add(new DayWeight(10,"初十",16));
		days.add(new DayWeight(11,"十一",9));
		days.add(new DayWeight(12,"十二",17));
		days.add(new DayWeight(13,"十三",8));
		days.add(new DayWeight(14,"十四",17));
		days.add(new DayWeight(15,"十五",10));
		days.add(new DayWeight(16,"十六",8));
		days.add(new DayWeight(17,"十七",9));
		days.add(new DayWeight(18,"十八",18));
		days.add(new DayWeight(19,"十九",5));
		days.add(new DayWeight(20,"二十",15));
		days.add(new DayWeight(21,"廿一",10));
		days.add(new DayWeight(22,"廿二",9));
		days.add(new DayWeight(23,"廿三",8));
		days.add(new DayWeight(24,"廿四",9));
		days.add(new DayWeight(25,"廿五",15));
		days.add(new DayWeight(26,"廿六",18));
		days.add(new DayWeight(27,"廿七",7));
		days.add(new DayWeight(28,"廿八",8));
		days.add(new DayWeight(29,"廿九",16));
		days.add(new DayWeight(30,"三十",6));
	}
	public static List<HourWeight> hours = new ArrayList<HourWeight>();
	static{
		hours.add(new HourWeight(1,"丑时",6));
		hours.add(new HourWeight(2,"丑时",6));
		hours.add(new HourWeight(3,"寅时",7));
		hours.add(new HourWeight(4,"寅时",7));
		hours.add(new HourWeight(5,"卯时",10));
		hours.add(new HourWeight(6,"卯时",10));
		hours.add(new HourWeight(7,"辰时",9));
		hours.add(new HourWeight(8,"辰时",9));
		hours.add(new HourWeight(9,"巳时",16));
		hours.add(new HourWeight(10,"巳时",16));
		hours.add(new HourWeight(11,"午时",10));
		hours.add(new HourWeight(12,"午时",10));
		hours.add(new HourWeight(13,"未时",8));
		hours.add(new HourWeight(14,"未时",8));
		hours.add(new HourWeight(15,"申时",8));
		hours.add(new HourWeight(16,"申时",8));
		hours.add(new HourWeight(17,"酉时",9));
		hours.add(new HourWeight(18,"酉时",9));
		hours.add(new HourWeight(19,"戌时",6));
		hours.add(new HourWeight(20,"戌时",6));
		hours.add(new HourWeight(21,"亥时",6));
		hours.add(new HourWeight(22,"亥时",6));
		hours.add(new HourWeight(23,"子时",16));
		hours.add(new HourWeight(0,"子时",16));
	}
	public static List<PersonDestiny> men = new ArrayList<PersonDestiny>();
	static{
		men.add(new PersonDestiny(21,"二两一钱",null,"短命非业谓大空 平生灾难事重重 凶祸频临陷逆境 终世困苦事不成",null));
		men.add(new PersonDestiny(22,"二两二钱",null,"身寒骨冷苦伶仃 此命推来行乞人 劳劳碌碌无度日 终年打拱过平生",null));
		men.add(new PersonDestiny(23,"二两三钱",null,"此命推来骨格轻 求谋作事事难成 妻儿兄弟应难许 别处他乡作散人",null));
		men.add(new PersonDestiny(24,"二两四钱",null,"此命推来福禄无 门庭困苦总难荣 六亲骨肉皆无靠 流浪他乡作老翁",null));
		men.add(new PersonDestiny(25,"二两五钱",null,"此命推来祖业微 门庭营度似稀奇 六亲骨肉如冰炭 一世勤劳自把持",null));
		men.add(new PersonDestiny(26,"二两六钱",null,"平生衣禄苦中求 独自营谋事不休 离祖出门宜早计 晚来衣禄自无休","此命为人性灵巧，胸襟通达。志气高强，少年勤学有功名，年青欠利。腹中多谋，有礼有义，有才有能做事勤俭。 立业思抱子，一生福禄无亏，与人干事反为不美。亲朋戚友，四海春风，末限光辉门庭。见善不欺，逢恶不怕，事有始终，吉人天相，四海扬名成家立业，安然到老，高楼大厦，妻宫硬无刑，子媳三人，只有一子送终，寿元七十七，卒于风光之中"));
		men.add(new PersonDestiny(27,"二两七钱",null,"一生作事少商量 难靠祖宗作主张 独马单枪空做去 早年晚岁总无长","此命为人性纯，不刚不柔，心所无毒，做事有始有终，池塘鸳鸯寻食吃，易聚易散，骨肉六亲不得力，财帛风云， 操心费力才及早，恨奋寒窗，原业破尽，重新白手成家，且过三十五，六方可成家立业，四十开外，行船逢顺风，五十岁安稳，末限滔滔事业兴，妻宫破配，子女伴架送终，寿元七十岁，卒于五月之中"));
		men.add(new PersonDestiny(28,"二两八钱",null,"一生行事似飘蓬 祖宗产业在梦中 若不过房改名姓 也当移徒二三通","此命为人多才能，心机灵巧，祖业凋零， 离乡别境可成事业，兄弟多力，驳杂多端， 为静处安然，出外有人敬重，可进四方之财， 有贵人扶持，逢凶化吉，勤俭一生无大难， 只是救人无功，恩中招怨，重义轻财，易聚易散，早年不能聚财，三十三方知艰难，凡事顺意，三十八。九。四十岁，称心如意，末限福如东海，寿比南山，只是妻宫有克，三子送终。寿元六十九，闯过八十一，卒于三月之中"));
		men.add(new PersonDestiny(29,"二两九钱",null,"初年运限未曾亨 纵有功名在后成 须过四旬才可立 移居改姓始为良","此命为人性燥，心直口快，有才能， 见善不欺，见恶不怕，兄弟六亲无力，量能宽大，但不能聚财，事有始终。 自立家计，出外方好，初限二十二。三至二十四。五不遂，二十七。八有好运到，犹如枯木逢春，中限四十九有险，五十多来古镜重磨，明月两圆，五十六。七。八末限。明月又被云侵，交七十方走大运，妻宫小配怕刑，克子。寿元七十七卒于风光之中"));
		men.add(new PersonDestiny(30,"三两",null,"劳劳碌碌苦中求 东奔西走何日休 若使终身勤与俭 老来稍可免忧愁","此命为人多才多能，心机灵巧，祖业凋零离乡别境可成家立业，兄弟少力， 驳杂多端，出外有贵人扶持，一生刑险，无大难， 只是救人无功，恩中招怨，重义轻财，财帛易聚易散，早年不聚财，三十三方知艰难，万事如意，三十八。九。四十岁，如意称心，三子送终，寿元; 六十九， 卒于三月之中"));
		men.add(new PersonDestiny(31,"三两一钱",null,"忙忙碌碌苦中求 何日云开见日头 难得祖基家可立 中年衣食渐无忧","此命推来敬重双亲， 有福有禄，六亲和睦，义气高强，少年勤学有功名，忠孝双全，心中无毒无贵则富，出外受人钦敬，四海闻名，老年荣华，限上无忧，一生安康，年青欠利，末限安享福禄"));
		men.add(new PersonDestiny(32,"三两二钱",null,"初年运蹇事难谋 渐有财源如水流 到得中年衣食旺 那时名利一齐收","此命为人性急，灵巧无亏，初限能聚财，虚名虚利，财来财去，有败有兴，一生衣禄无亏，中限交来，渐渐称心，求谋顺利，出外有人恭敬，一身受贵，若要问其消息，重事业，兴家业旺，其年运到滔滔财源来。滚滚利丰盈， 春光花自发，微风细雨生， 四十六。七。八交末运，移花接木桂花香，夫妻偕老， 寿元八十之外，子孙福禄荣昌，卒于腊月之中"));
		men.add(new PersonDestiny(33,"三两三钱",null,"早年做事事难成 百年勤劳枉费心 半世自如流水去 后来运到始得金","此命为人性巧心灵，能假成真，口快心无，恩中招怨，君子钦敬，小人嫉妒，骨肉无援，志在四方，身心健康，前运乘荫少重树，中限轻财，大运交来，声明可望，万业焕新，名利振建，五人金石皆至，发须有心田，以后小事宜注意，才有子媳，寿元八十三， 卒于三月之中"));
		men.add(new PersonDestiny(34,"三两四钱",null,"此命福气果如何 僧道门中衣禄多 离祖出家方为妙 朝晚拜佛念弥陀","自成自立安享福至三十六至四十六，财不谋而自至，福不求而自得。有贵人助力，家康安宁， 妻宫若要无刑，猪羊蛇不可配，龙虎马牛方得安，虽有二子，终身带暗方可，兄弟六亲如冰炭，在家不得安然， 限初驳杂多端，劳碌奔波，不能聚财，常有忧虑，寿元七十八，卒于三月之中"));
		men.add(new PersonDestiny(35,"三两五钱",null,"生平福量不周全 祖业根基觉少传 营事生涯宜守旧 时来衣食胜从前","此命为人品性醇和，做事忠直，志气高傲，与人做事恩中招怨， 六亲兄弟不得力， 祖业全无，早年驳杂多端，独马单枪初限命运来，二十八九三十岁，未曾交运都说好，至到交时苦哀哉。三十五六到四十，犹如金秋菊迎秋放， 心机用尽方逢贵，末限交来始称怀，祖业有破后又重兴，犹如松木逢春再开花，妻宫优虑无刑，寿元五十七，有限至六十九，二子送老五十七，有限至， 三子送老，寿元八十一，卒于十月之中"));
		men.add(new PersonDestiny(36,"三两六钱",null,"不须劳碌过平生 独自成家福不轻 早有福星常照命 任君行去百般成","此命为人品性刚直， 做事公开，有才能，有机变。不管休息，六亲兄弟不得力，祖业无靠， 白手成家立业，末运多驳杂， 不能聚财， 好一双抓钱手，没有一个聚钱斗，此命蜘蛛， 结网朝圆夜不圆，做几翻败几翻，只能稳步成家计， 谁知又被狂风吹，初限二十三，似明月被云侵，三十开外恰似日头重升，二子送终寿元五十七岁，过此八十八， 卒于秋天之中"));
		men.add(new PersonDestiny(37,"三两七钱",null,"此命般般事不成 弟兄少力自孤行 虽然祖业须微有 来得明时去不明","此命为人品性刚直，做事公开有才能，不管休息，六亲兄弟不得力，祖业难靠，白手成家，早年驳杂多端，不能聚财，不欺侮人，有意气，心神不定，易成喜怒，初限奔波，离别他境可成家计，改换门庭，中限未得如意，末限环怀妻宫，方为刑克，子媳虽有不得力，只好真假送终，寿元七十七，卒于十月之中"));
		men.add(new PersonDestiny(38,"三两八钱",null,"一身骨肉最清高 早入簧门姓氏标 待到年将三十六 蓝衫脱去换红袍","此命为人品性刚直， 做事公开有才能， 不管休息，六亲兄弟不得力， 祖业难靠， 好一双抓钱手，没有一个聚钱斗，蜘蛛结网朝圆夕不圆，做几翻败几翻，只能稳然成家业，谁知又是遇狂风，初限二十三四，又如明月被云侵，三十外来交四十恰似日头又重升，终交末运方为贵，渐渐荣昌旺祖宗，妻宫继配方偕老，子媳二人送终，寿元五十七，过此六十九， 卒于秋天之中"));
		men.add(new PersonDestiny(39,"三两九钱",null,"此命终身运不通 劳劳作事尽皆空 苦心竭力成家计 到得那时在梦中","此命为人灵机性巧， 胸襟通达，志气高，少年勤学有功名之格， 青年欠利， 腹中多谋，有礼仪，有才能， 做事勤俭，一生福禄无，与人干事反为不美，六亲骨肉可靠，交朋友，四海春风。中限光耀门庭，见善不欺，逢恶不怕，事有始终，量能宽大，义济分明，吉人天相，四海闻名，末限成家立业，安然到老，高楼大厦，妻宫两硬无刑，子媳三人，只有一终，寿元七十七，卒于风光之中"));
		men.add(new PersonDestiny(40,"四两",null,"平生衣禄是绵长 件件心中自主张 前面风霜多受过 后来必定享安康","此命为人性燥， 心直口快，有才能，见善不欺，逢恶不怕，事有始终，量能宽大，不聚财，祖业破败，六亲兄弟不得力，自立家计， 出外方好，初限二十三四至二十七八九有好运，犹如枯木逢春，中限四十九有险。四十开外， 旧镜重磨，明月正圆，五十六七交大运，寿元七十七，卒于春光之中"));
		men.add(new PersonDestiny(41,"四两一钱",null,"此命推来自不同 为人能干异凡庸 中年还有逍遥福 不比前时运来通","此命性直气高， 有口无心，祖业未交，离别他境，万事可成，六亲骨肉不得力，自成家计。学习经营四方闻名，初限奔波驳杂，不能聚财，交过三十八九，方可成家，四十五六方能顺意，末限犹如三春杨柳，枝枝生绿叶，晚景处处红， 妻宫两硬无克，子媳真假送老，寿元四十七，过此可到六十六，卒于九月之中"));
		men.add(new PersonDestiny(42,"四两二钱",null,"得宽怀处且宽怀 何用双眉皱不开 若使中年命运济 那时名利一起来","此命为人操劳，白手成家 ，与人出力事不成，离祖之命，成家三翻四次，用尽心机不得开怀，若要安乐享福，要到三十六到四十六不谋自得，福不求自至，有贵人助力，家庭安然，妻宫若要无刑，猴猪羊蛇不可配，龙虎马牛方得意，六亲兄弟如冰碳， 在家不能安然，初限驳杂多端，不能聚财，常常忧虑，寿元七十八，二子送老，卒于三月之中"));
		men.add(new PersonDestiny(43,"四两三钱",null,"为人心性最聪明 作事轩昂近贵人 衣禄一生天注定 不须劳碌是丰亨","此命为人性燥刚强，平生不受亏，多技多能，祖业冰碳，骨肉风云，兄弟画饼充饥，六亲望梅止渴，劳心见早，发福见迟，独立成家，能聚财超过三十开外，方得意开怀。中限之命能进四方之财，出外逢贵人助力，艺术精善经营，方能兴旺，上业迟有一病相侵至末限，方得享福，妻宫匹配，龙虎马牛可配，二子送老，寿元八十岁"));
		men.add(new PersonDestiny(44,"四两四钱",null,"万事由天莫苦求 须知福碌赖人修 当年财帛难如意 晚景欣然便不优","此命为人忠直，心慈性燥，深谋远虑，心中多劳，贵人敬重，与人做事反为不美，六亲冰炭，初限不足，中限渐渐佳境，名利可佳，刚柔有济。二十九交来顺意，东北遇佳音，可通花甲一二，突然大风，天赐麒麟送老，寿元八十五，卒于冬月之中"));
		men.add(new PersonDestiny(45,"四两五钱",null,"名利推求竟若何 前番辛苦后奔波 命中难养男和女 骨肉扶持也不多","此命为人品性不刚不柔，心所无毒，自当自担，离祖之命，做事有始终，池塘鸳鸯寻食吃， 或聚或散，骨肉六亲不得力，如帛如风，劳心费力多成败。初限运寒多驳杂，祖业破败，重新白手成家，至三十五六方能成家立业，四十开外，如船遇顺风，五十多岁安稳， 末限滔滔事业兴，妻宫硬配子媳伴架送终，寿元七十五，卒于五月之中"));
		men.add(new PersonDestiny(46,"四两六钱",null,"东西南北尽皆通 出姓移居更觉隆 衣禄无穷无数定 中年晚景一般同","此命为人心慈性燥， 有口无心有粗有细， 一生奔波，六亲无靠，无大难，妻宫两硬无刑，祖业凋零， 自立家计，早业如同败落萍， 劳心用下一条计， 交三十五六七八九岁，又平平度过几春秋，六十前后花开日，花开又遭雨来淋，必定小人加暗害，平日之中要小心，只有一子送终，，寿元七十三，卒于冬月之中"));
		men.add(new PersonDestiny(47,"四两七钱",null,"此命推求旺末年 妻荣子贵自怡然 平生原有滔滔福 可卜财源若水泉","此命为人品性纯和， 做事公道， 忠于待人气质高，为人干事恩中招怨， 兄弟不得力 ，祖业微微，早年驳杂多端，时来骨肉情， 财源是归命， 独马单枪初限运来二十八九三十岁 ，末限交运都好，反倒交时苦哀，三十六至四十岁，犹如金秋菊遇秋放，心机用尽方为贵，末运交来恰称怀，祖业有破， 家业重兴，好似枯木逢春再开花，孤子送老，五十九有一限，到六十九，寿元八十二，卒于冬月之中"));
		men.add(new PersonDestiny(48,"四两八钱",null,"初年运道未曾通 几许蹉跎命亦穷 兄弟六亲无依靠 一生事业晚来整","此命为人性燥， 能随机应变 ，常近贵人，祖业无成，骨肉亲朋少义，自立家计。 初限交来财运如霜雪，中年略可成家，大运交来顺家业， 妻有可， 小配无刑，子媳三人，只有一子送终，寿元七十七，卒于冬月之中"));
		men.add(new PersonDestiny(49,"四两九钱",null,"此命推来福不轻 自成自立显门庭 从来富贵人钦敬 使婢差奴过一生","此命为人品性纯和，做事勤俭，恩中招怨，兄弟有克，亲朋相怨，赔酒赔饭，反说不美，初限贫愁，交过二十六七岁如逆水行舟，不能聚财， 中限驳杂多端，刑妻克子，交过四十岁，方可成家立业，般般如意，件件称心，至四十六七有一灾，宁可损财交过， 后有十年好运来，家中钱财聚备，三子送老，寿元七十三"));
		men.add(new PersonDestiny(50,"五两",null,"为利为名终日劳 中年福禄也多遭 老来自有财星照 不比前番目下高","此命为人伶俐乖巧，有机变， 平生无大难，祖业无靠自成立，白手成家，亲朋冷落， 兄弟少力，可得四方之财，好一双挣钱手，没有一个聚钱斗， 满面春光人道好，一生不足自家知。妻迟子晚，初限奔波， 中限四十岁方交大运，犹如枯木逢春，四十九岁有一灾，其年福星照，有十年大运，财禄吉昌，妻宫两硬同偕老， 子媳一双可送终寿元六十九，卒于冬月之中"));
		men.add(new PersonDestiny(51,"五两一钱",null,"一世荣华事事通 不须劳碌自亨通 兄弟叔侄皆如意 家业成时福禄宏","此命为人做事有能力，且能随机应变， 性燥能知其轻重，交朋结友如兄弟，气量宽宏，逢恶不怕，平生正直，无大难刑险，初限福禄无亏，子媳晚招可实得，四十五至五十，末限通达吉昌，福禄无亏，财源稳定，风衣足食，高堂妻宫友好，二子三女送终，寿元 八十，卒于九月之中"));
		men.add(new PersonDestiny(52,"五两二钱",null,"一世亨通事事能 不须劳苦自然宁 宗族有光欣喜甚 家产丰盈自称心","此命为人多才能，心机灵变，祖业凋零，离乡可成家计，兄弟少力，驳杂多端，为人静处安然，出外有人敬重，可进四方之财，有贵人扶持，逢凶化吉，一生无刑险，无大难，只是救世人无功，重义轻财，财帛易聚易散，早年聚财，凡事顺意，三十八九四十岁如意称心。 末限福如东海，寿比南山，只是妻宫两硬无刑，有三子二女送终，寿元六十五，卒于冬月之中"));
		men.add(new PersonDestiny(53,"五两三钱",null,"此格推来福泽宏 兴家立业在其中 一生衣食安排定 却是人间一福翁","此命推来重拜双亲，八字有福有禄， 气质高昂，少年勤学功名不就，忠孝两全，心善无毒，非福则贵，出外有人钦敬，四海扬名， 到老繁华，限上无忧，一世健康，青年欠利，末限安享福禄，白鹤先生云，此骨三限之骨，子孙王相之局，初限早成家计， 辛勤劳苦，中限渐渐发财，重整门庭，末限荣华富贵，妻宫小配无刑，有三子二女送终，寿元八十二，卒于冬月之中"));
		men.add(new PersonDestiny(54,"五两四钱",null,"此格详采福泽宏 诗书满腹看功成 丰衣足食多安稳 正是人间有福人","此命为人刚强，劳心费力，移祖居住，有能力，自立得安然，短轻识重，坏事不做， 有老怜贫心口无毒，但一生不足，子媳难靠，初限之中小发达，早成家计得安康，四十八九交末运，渐渐谋事事业成，干业而就， 老来亨通财源茂盛，老来荣华，妻宫有克，两妻无刑，子媳四个，只有一子送终，寿元四十九，过此卒于十二月之中"));
		men.add(new PersonDestiny(55,"五两五钱",null,"策马扬鞭争名利 少年作事费筹论 一朝福禄源源至 富贵荣华显六亲","此命为人灵性机巧，初限尚不聚财， 只是虚名虚利，财来财去，有败有成，一生勤学自有功名， 有衣禄，福星照命，中限交来可称心，求谋如意， 出外有人恭敬，一生受贵，要问其他消息，事后兴家发达，壮年滔滔财源旺，迎春花正发，微风细雨生，四十九交末运， 移花接木桂花香，夫妻百年同偕老，寿元八十之外，福禄荣昌"));
		men.add(new PersonDestiny(56,"五两六钱",null,"此格推来礼义通 一身福禄用无穷 甜酸苦辣皆尝过 滚滚财源盈而丰","此命为人性巧心灵，有口无心， 事不保密，少年劳碌难免，志在四方，身心健康，前运乘荫少种树， 怀才不遇，三十九财来无归日，中限轻财，大举随行，移运得安然，成名可望，旧业还新，名利亨通，此命小事宜放松，方有子媳， 寿元八十二，卒于冬月之中"));
		men.add(new PersonDestiny(57,"五两七钱",null,"福禄丰盈万事全 一身荣耀乐天年 名扬威震人争羡 此世逍遥宛似仙","此命为人心性灵巧，做事细致， 足智多谋，志气高昂，少年勤学，名利成就，适逢快乐， 气量宽宏，财禄有余，犹如锦上添花。中限交来自成自立， 渐渐荣昌，招人进财，妻子晚配为美，四十至四十五六岁，看子成名，末限多得意，家中财产甚丰隆，妻宫有克，二子送终，寿元七十三，卒于正月之中"));
		men.add(new PersonDestiny(58,"五两八钱",null,"平生衣食自然来 名利双全富贵偕 金榜题名登甲第 紫袍玉带走金阶","此命为人忠直，做事有头有尾，身清志高，六亲无靠，兄弟少帮，妻宫重并，子媳虚花，业不招命，该他乡创业，外出四方买卖之命， 只是与人干事恩中招怨，仅为不美，早限财去财来，中限劳苦奔波，一子送终，寿元六十三，卒于四月之中"));
		men.add(new PersonDestiny(59,"五两九钱",null,"细推此格秀而清 必定才高学业成 甲第之中应有分 扬鞭走马显威荣","此命为人性情暴躁，刚强，平生不受亏，所谓量大多技多能，受人尊敬，祖业凋零， 兄弟只可画饼充饥，亲戚则是望梅止渴，劳心见早，发福见迟， 独立成家，只是早聚财逢凶化吉，驳杂交过二十中外，方得意开怀， 中限之命可进四方之财，出外有贵人助力，可精手艺营业， 方能兴家立业，此间或有小疾相侵，再交限方得安然，坐享福禄，妻宫之配龙虎马牛，一子送终，寿元八十岁，卒于六月之中"));
		men.add(new PersonDestiny(60,"六两",null,"一朝金榜快题名 显祖荣宗大器成 衣禄定然无欠缺 田园财帛更丰盈","此命为人机灵，胸襟发达，志气高强，少年勤学，有功名之格，青年欠利，腹中多谋，有礼有义，有才能，做事勤俭，一生福禄无亏，与人做事反为不美， 兄弟骨肉情，交朋友四海扬名，中限光辉门庭，量能宽大，义利分明，末限成家立业，安然到老，高楼大厦，妻宫两硬无性，子媳三人只有一人送终，寿元七十七，卒于风光之中"));
		men.add(new PersonDestiny(61,"六两一钱",null,"不作朝中金榜客 定为世上大财翁 聪明天付经书熟 名显高褂自是荣",null));
		men.add(new PersonDestiny(62,"六两二钱",null,"此命生来福不穷 读书必定显亲宗 紫衣玉带为卿相 富贵荣华孰与同","此命为人忠直宽厚，心所无毒，性巧，吉人天相，深谋远虑，心中多劳，有人钦敬，美中不足，中限渐渐入佳境，名利可佳刚有情，二十九交来阳春暖，东北佳音，天津四通，花甲一二岁，大顺天赐麒麟送老，寿元八十五，卒于冬月之中"));
		men.add(new PersonDestiny(63,"六两三钱",null,"命主为官福禄长 得来富贵实非常 名题雁塔传金榜 大显门庭天下扬",null));
		men.add(new PersonDestiny(64,"六两四钱",null,"此格威权不可当 紫袍金带尘高堂 荣华富贵谁能及 万古留名姓氏扬",null));
		men.add(new PersonDestiny(65,"六两五钱",null,"细推此命福非轻 富贵荣华孰与争 定国安邦人极品 威声显赫震寰瀛",null));
		men.add(new PersonDestiny(66,"六两六钱",null,"此格人间一福人 堆金积玉满堂春 从来富贵有天定 金榜题名更显亲",null));
		men.add(new PersonDestiny(67,"六两七钱",null,"此命生来福自宏 田园家业最高隆 平生衣禄盈丰足 一路荣华万事通",null));
		men.add(new PersonDestiny(68,"六两八钱",null,"富贵由天莫苦求 万事家计不须谋 十年不比前番事 祖业根基千古留",null));
		men.add(new PersonDestiny(69,"六两九钱",null,"君是人间福禄星 一生富贵众人钦 总然衣禄由天定 安享荣华过一生",null));
		men.add(new PersonDestiny(70,"七两",null,"此命推来福不轻 何须愁虑苦劳心 荣华富贵已天定 正笏垂绅拜紫宸",null));
		men.add(new PersonDestiny(71,"七两一钱",null,"此命生成大不同 公侯卿相在其中 一生自有逍遥福 富贵荣华极品隆",null));
		men.add(new PersonDestiny(72,"七两二钱",null,"此命推来天下隆 必定人间一主公 富贵荣华数不尽 定为乾坤一蛟龙",null));
	}
	public static List<PersonDestiny> women = new ArrayList<PersonDestiny>();
	static{
		women.add(new PersonDestiny(21,"二两一钱",null,"生身此命运不通 乌云盖月黑朦胧 莫向故园载花木 可来幽地种青松",null));
		women.add(new PersonDestiny(22,"二两二钱",null,"女命孤冷独凄身 此身推来路乞人 操心烦恼难度日 一生痛苦度光阴",null));
		women.add(new PersonDestiny(23,"二两三钱",null,"女命生来轻薄人 营谋事作难称心 六亲骨肉亦无靠 奔走劳碌困苦门",null));
		women.add(new PersonDestiny(24,"二两四钱",null,"女命推来福禄无 治家艰难辛苦多 丈夫儿女不亲爱 奔走他乡作游姑",null));
		women.add(new PersonDestiny(25,"二两五钱",null,"此命一身八字低 家庭艰辛多苦妻 娘家亲友冷如炭 一生勤劳多忧眉",null));
		women.add(new PersonDestiny(26,"二两六钱",null,"平生依禄但苦求 两次配夫带忧愁 咸酸苦辣他偿过 晚年衣食本无忧",null));
		women.add(new PersonDestiny(27,"二两七钱",null,"此格做事单独强 难告夫君作主张 心问口来口问心 晚景衣禄宜自生",null));
		women.add(new PersonDestiny(28,"二两八钱",null,"女命生来八字轻 为善作事也无因 你把别人当亲生 别人对你假殷情",null));
		women.add(new PersonDestiny(29,"二两九钱",null,"花支艳来硬性身 自奔自力不求人 若问求财方可止 在苦有甜度光阴",null));
		women.add(new PersonDestiny(30,"三两",null,"女命推来比郎强 婚姻大事碍无障 中年走过坎坷地 末年渐经行前强 ",null));
		women.add(new PersonDestiny(31,"三两一钱",null,"早年行运在忙头 劳碌奔波苦勤求 费力劳心把家立 后来晚景名忧愁",null));
		women.add(new PersonDestiny(32,"三两二钱",null,"时逢运来带吉神 从有凶星转灰尘 真变假来假成真 结拜弟妹当亲生",null));
		women.add(new PersonDestiny(33,"三两三钱",null,"初限命中有变化 中年可比树落花 勤俭持家难度日 晚年成业享荣华",null));
		women.add(new PersonDestiny(34,"三两四钱",null,"矮巴勾枣难捞枝 看破红尘最相宜 谋望求财空费力 婚姻三娶两次离",null));
		women.add(new PersonDestiny(35,"三两五钱",null,"女子走冰怕冰薄 出行交易受残霜 婚姻周郎休此意 官司口舌须相加",null));
		women.add(new PersonDestiny(36,"三两六钱",null,"忧悉常锁两眉间 家业挂心不等闲 从今以后防口角 任意行移不相关",null));
		women.add(new PersonDestiny(37,"三两七钱",null,"此命推来费运多 若作摧群受折磨 山路崎岖吊下耳 左插右安心难留",null));
		women.add(new PersonDestiny(38,"三两八钱",null,"凤鸣岐山四方扬 女命逢此大吉昌 走失夫君音信有 晚年衣禄财盈箱 ",null));
		women.add(new PersonDestiny(39,"三两九钱",null,"女命推来运未能 劳碌奔波一场空 好似俊鸟在笼锁 中年未限凄秋风",null));
		women.add(new PersonDestiny(40,"四两",null,"目前月令运不良 千辛万苦受煎熬 女身受得多苦难 晚年福禄比密甜",null));
		women.add(new PersonDestiny(41,"四两一钱",null,"此命推来一般艰 女子为人很非凡 中年逍遥多自在 晚年更比中年超 ",null));
		women.add(new PersonDestiny(42,"四两二钱",null,"杜井破废已多年 今有泉水出来鲜 资生济竭人称美 中运来转喜安然",null));
		women.add(new PersonDestiny(43,"四两三钱",null,"推车靠涯道路通 女名求财也无穷 婚姻出配无阻碍 疾病口舌离身躬 ",null));
		women.add(new PersonDestiny(44,"四两四钱",null,"夜梦金银醒来空 立志谋业运不通 婚姻难成交易散 夫君趟失未见踪",null));
		women.add(new PersonDestiny(45,"四两五钱",null,"女命终身驳杂多 六亲骨肉不相助 命中男女都难养 劳碌辛苦还奔波 ",null));
		women.add(new PersonDestiny(46,"四两六钱",null,"孤舟行水离沙滩 离乡出外早过家 是非口舌皆无碍 婚姻合配紫微房",null));
		women.add(new PersonDestiny(47,"四两七钱",null,"时来运转喜开颜 多年枯木逢春花 枝叶重生多茂盛 凡人见得都赞夸",null));
		women.add(new PersonDestiny(48,"四两八钱",null,"一朵鲜花镜中开 看着极好取不来 劝你休把镜花想 此命推来主可癫 ",null));
		women.add(new PersonDestiny(49,"四两九钱",null,"一生为人福宏名 心兹随君显门庭 容貌美丽惹人爱 银钱富足万事成",null));
		women.add(new PersonDestiny(50,"五两",null,"马氏太公不相和 好命逢此忧凝多 恩人无义反为仇 是非平地起风波",null));
		women.add(new PersonDestiny(51,"五两一钱",null,"肥羊一群入出场 防虎逢之把口张 适口充饥心欢喜 女命八字大吉昌 ",null));
		women.add(new PersonDestiny(52,"五两二钱",null,"顺风行舟扯起帆 上天又助一顺风 不用费力逍遥去 任意顺行大亨通",null));
		women.add(new PersonDestiny(53,"五两三钱",null,"此命相貌眉目清 文武双全功名成 一生衣禄皆无缺 可算世上积福人",null));
		women.add(new PersonDestiny(54,"五两四钱",null,"运开满腹好文章 谋事求财大吉祥 出行交易多得稳 到处亨通姓名扬",null));
		women.add(new PersonDestiny(55,"五两五钱",null,"发政旅仁志量高 女命求财任他乡 交舍婚姻多有意 无君出外有音耗",null));
		women.add(new PersonDestiny(56,"五两六钱",null,"明珠辉吐离埃来 女有口有清散开 走失郎君当两归 交易有成永无灾",null));
		women.add(new PersonDestiny(57,"五两七钱",null,"游鱼戏水被网惊 踊身变化入龙门 三根杨柳垂金钱 万朵桃花显价能",null));
		women.add(new PersonDestiny(58,"五两八钱",null,"此命推来转悠悠 时运未来莫强求 幸得今日重反点 自有好运在后头 ",null));
		women.add(new PersonDestiny(59,"五两九钱",null,"雨雪载途活泥泞 交易不安难出生 疾病还拉婚姻慢 谋望求财事难寻",null));
		women.add(new PersonDestiny(60,"六两",null,"女命八字喜气和 谋事求财吉庆多 口舌渐消疾病少 夫君走别归老窠 ",null));
		women.add(new PersonDestiny(61,"六两一钱",null,"缘木求鱼事多难 虽不得鱼无害反 若是行险弄巧地 事不遂心枉安凡 ",null));
		women.add(new PersonDestiny(62,"六两二钱",null,"指日高升气象新 走失待人有信音 好命遇事遂心好 伺病口舌皆除根",null));
		women.add(new PersonDestiny(63,"六两三钱",null,"五官脱运难抬头 妇命须当把财求 交易少行有人助 一生衣禄不须愁",null));
		women.add(new PersonDestiny(64,"六两四钱",null,"俊鸟曾得出胧中 脱离为难显威风 一朝得意福力至 东南西北任意通",null));
		women.add(new PersonDestiny(65,"六两五钱",null,"女命推来福非轻 兹善为事受人敬 天降文王开基业 八百年来富贵门",null));
		women.add(new PersonDestiny(66,"六两六钱",null,"时来运转闺阁楼 贤德淑女君子求 击鼓乐之大吉庆 女命逢此喜悠悠",null));
		women.add(new PersonDestiny(67,"六两七钱",null,"乱丝无头定有头 碰得亲事暂折磨 交易出行无好处 谋事求财心不遂 ",null));
		women.add(new PersonDestiny(68,"六两八钱",null,"水底明月不可捞 女命早限运未高 交易出行难获利 未运终得渐见好 ",null));
		women.add(new PersonDestiny(69,"六两九钱",null,"太公封祖不非凡 女子求财稳如山 交易合伙多吉庆 疾病口角遗天涯 ",null));
		women.add(new PersonDestiny(70,"七两",null,"本命推断喜气新 恰遇郎君金遂心 坤身来交正当运 富贵衣禄乐平生 ",null));
		women.add(new PersonDestiny(71,"七两一钱",null,"此命推来宏运交 不须再愁苦劳难 一生身有衣禄福 安享荣华胜班超",null));
	}
	
	
	/** 设置menu的关于按钮值*/
	public static final int ABOUT = 0;
	/** 设置menu的退出按钮值*/
	public static final int EXIT = 1;
	/** 设置关于文字的字体大小*/
	public static final float ABOUT_TEXT_SIZE = 20f;
	/** 设置应用的下载路径*/
	public static final String SHARE_URL = " http://m.baidu.com/s?st=10a001&tn=webmkt&pre=web_am_index&word=%E5%85%AB%E5%AD%97%E7%A7%B0%E9%AA%A8%E5%8D%A0%E5%8D%9C ";
	

	/** 百度日志应用参数*/
	public static final String TAG = "Baidu Mobstat";
}
