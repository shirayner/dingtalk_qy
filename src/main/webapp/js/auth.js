dd.config({
	agentId : _config.agentId,
	corpId : _config.corpId,
	timeStamp : _config.timeStamp,
	nonceStr : _config.nonceStr,
	signature : _config.signature,
	jsApiList : [                           //需要调用的借口列表 
		'runtime.info',
		'biz.contact.choose',            //选择用户接口  
		'device.notification.confirm', 
		'device.notification.alert',   //confirm,alert,prompt都是弹出小窗口的接口     
		'device.notification.prompt', 
		'biz.ding.post',
		'biz.util.openLink' ,
		'biz.util.uploadImageFromCamera',
		'biz.util.uploadImage',
		'biz.util.scan'

		]
});

dd.ready(function() {  

	document.getElementById("yanzheng").innerHTML = "验证成功";  

	document.querySelector('#ceshi').onclick = function () {
		alert("ceshiaaa");
	};




	/** 1.获取容器信息
	 *获取容器信息，返回值为ability:版本号，也就是返回容器版本 
	 *用来表示这个版本的jsapi的能力，来决定是否使用jsapi 
	 */  
	dd.runtime.info({  
		onSuccess : function(info) {  
			logger.e('runtime info: ' + JSON.stringify(info));  
		},  
		onFail : function(err) {  
			logger.e('fail: ' + JSON.stringify(err));  
		}  
	});  	



	/**2.获取免登授权码 CODE
	 * 
	 */
	dd.runtime.permission.requestAuthCode({  
		corpId : _config.corpId,  
		onSuccess : function(info) {                                                   //成功获得code值,code值在info中  
			alert('authcode: ' + info.code);  
			/* 
			 *$.ajax的是用来使得当前js页面和后台服务器交互的方法 
			 *参数url:是需要交互的后台服务器处理代码，userInfoServlet
			 *参数type:指定和后台交互的方法，因为后台servlet代码中处理Get和post的doGet和doPost 
			 *data:负责传递请求参数
			 *其中success方法和error方法是回调函数，分别表示成功交互后和交互失败情况下处理的方法 
			 */
			$.ajax({  
				type : "POST",
				url : "http://rayner.nat300.top/dingtalk_qy/userInfoServlet",
				data : {
					code : info.code
				},
				success : function(data, status, xhr) {
					alert(data);
					//接收后端发送过来的用户信息
					var userInfo = JSON.parse(data);

					//收到用户信息后所做的处理
					document.getElementById("userName").innerHTML = userInfo.name;
					document.getElementById("userId").innerHTML = userInfo.userid;

					// 图片
					if(info.avatar.length != 0){
						var img = document.getElementById("userImg");
						img.src = info.avatar;
						img.height = '200';
						img.width = '200';
					}

				},
				error : function(xhr, errorType, error) {  
					logger.e("yinyien:" + _config.corpId);  
					alert(errorType + ', ' + error);  
				}  
			});  

		},  
		onFail : function(err) {                                                       //获得code值失败  
			alert('fail: ' + JSON.stringify(err));  
		}  
	});  


	/** 3.上传图片
	 * 
	 */
	document.querySelector('#uploadImg').onclick = function () {
		dd.biz.util.uploadImage({
			compression:false,//(是否压缩，默认为true)
			multiple: true, //是否多选，默认false
			max: 3, //最多可选个数
			//quality: 50, // 图片压缩质量, 
			//resize: 50, // 图片缩放率
			stickers: {   // 水印信息
				time: "08:35",
				dateWeather: "2017.10.17 周八·晴转多云 20℃",
				username: "石锐",
				address: "海上·上海"
			},
			onSuccess : function(result) {
				//onSuccess将在图片上传成功之后调用
				alert("上传成功"); 
				alert(result[0]); 
				var len=result.length;
				alert(len);





				//循环上传图片
				for (var i = 0; i < result.length; i++) {

					var imgUrl=result[i];
					alert(imgUrl);


					$.ajax({  
						type : "POST",
						url  : "http://rayner.nat300.top/dingtalk_qy/uploadImgServlet",
						data : {
							imgUrl : imgUrl
						},
						success : function(data, status, xhr) {
							alert(data);

						},
						error : function(xhr, errorType, error) {  
							alert(errorType + ', ' + error);  
						}  
					});  






				}




			},
			onFail : function(err) {
				alert("上传失败");
				alert("上传失败："+JSON.stringify(err));

			}
		})
	};




	/** 4.拍照上传图片
	 * 
	 */
	document.querySelector('#uploadImgFromCamera').onclick = function () {
		dd.biz.util.uploadImageFromCamera({
			compression: false,//(是否压缩，默认为true)
			//quality: 50, // 图片压缩质量, 
			//resize: 50, // 图片缩放率
			stickers: {   // 水印信息
				time: "08:35",
				dateWeather: "2017.10.17 周八·晴转多云 20℃",
				username: "石锐",
				address: "海上·上海"
			},
			onSuccess : function(result) {
				//onSuccess将在图片上传成功之后调用
				alert("上传成功"); 
				alert(result[0]); 
				var len=result.length;
				alert(len);


				//循环上传图片
				for (var i = 0; i < result.length; i++) {

					var imgUrl=result[i];
					alert(imgUrl);


					$.ajax({  
						type : "POST",
						url  : "http://gkv2vk.natappfree.cc/DingTalk_Demo/uploadImgServlet",
						data : {
							imgUrl : imgUrl
						},
						success : function(data, status, xhr) {
							alert(data);

						},
						error : function(xhr, errorType, error) {  

							alert(errorType + ', ' + error);  
						}  
					});  






				}




			},
			onFail : function(err) {

				alert("上传失败："+JSON.stringify(err));

			}
		})
	};

	/**5.扫码
	 * 
	 */
	document.querySelector('#qrcode').onclick = function () {
		dd.biz.util.scan({
			type: String , // type 为 all、qrCode、barCode，默认是all。
			onSuccess: function(data) {
				//onSuccess将在扫码成功之后回调
				alert(JSON.stringify(data));

				var content=data.text;
				alert(content);

				$.ajax({
					type : "POST",
					url : "http://gkv2vk.natappfree.cc/DingTalk_Demo/qrservlet",
					data : {
						result : data.text
					},
					dataType : "text",
					success : function(data) {
						alert(data);
					},
					error : function(xhr, errorType, error) {  

						alert(errorType + ', ' + error);  
					}  

				});



			},
			onFail : function(err) {
				alert(JSON.stringify(data));
			}
		})
	};	




});  


//在dd.config函数验证失败时执行 dd.error
dd.error(function(err) {                                             //验证失败  
	alert("进入到error中");  
	document.getElementById("userName").innerHTML = "验证出错";  
	alert('dd error: ' + JSON.stringify(err));  
});  