package com.servepdf.test
{
	import flexunit.framework.TestSuite;
	
	public class AllServePDFTests
	{
		public static function suite(): TestSuite
			{ 
				var ts:TestSuite = new TestSuite()
				ts.addTest(new PDFDataTest("testContent"))
				return ts;
			}
	}
}