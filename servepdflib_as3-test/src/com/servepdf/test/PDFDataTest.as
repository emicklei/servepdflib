package com.servepdf.test
{
	import com.servepdf.dto.Content;
	import com.servepdf.dto.TextField;
	
	import flexunit.framework.TestCase;

	public class PDFDataTest extends TestCase
	{
		public function PDFDataTest(methodName:String=null)
		{
			super(methodName);
		}
		public function testContent():void {
			var c:Content = new Content()
			c.fields.push(new TextField("name","value"))
			trace(c.toXML())
		}
	}
}