package uz.java.yandex_translater.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.asif.gsonpojogenerator")
public class Respons implements Serializable {

	@SerializedName("code")
	private int code;

	@SerializedName("text")
	private List<String> text;

	@SerializedName("lang")
	private String lang;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setText(List<String> text){
		this.text = text;
	}

	public List<String> getText(){
		return text;
	}

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	@Override
 	public String toString(){
		return 
			"AnswerTranslate{" + 
			"code = '" + code + '\'' + 
			",text = '" + text + '\'' + 
			",lang = '" + lang + '\'' + 
			"}";
		}
}