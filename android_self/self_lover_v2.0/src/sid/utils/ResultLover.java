package sid.utils;

public class ResultLover {
	//设置结果对象
	private Object obj = null;
	//设置结果信息
	private String msg = null;
	//设置结果是否正确
	private boolean result = true;
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

}
