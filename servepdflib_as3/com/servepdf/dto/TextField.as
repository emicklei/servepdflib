/*
       Copyright 2007 Ernest Micklei, PhilemonWorks.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. 
*/
package com.servepdf.dto
{
	public class TextField
	{
		public var name:String;
		public var value:String;
		
		public function TextField(newName:String,newValue:String){
			super()
			this.name = newName
			this.value = newValue
		}
		public function validate():void {
			if (name == null || name.length == 0)
				throw new ArgumentError("TextField has invalid name")
			if (value == null)
				throw new ArgumentError("TextField has invalid value")				
		}		
	}
}