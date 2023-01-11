// 思路 ：打开聊天框
//   1.首先显示系统发送来的常见问题
//   2. 用户 根据发 系统发来的消息 来选择相应的问题解答 （可以将问题和解答一起发到前端 然后解析就行）
//    3.用户发消息 后台匹配问题 选择解答 到前端
//    4.用户使用留言系统
// 1.完成 聊天框加载 加载显示系统发送来的常见问题
$(document).ready(function(){
    const questionId=$("#historyMsg");//存入问题编号(id)
    const questionContent=$("#control");//存入问题解决方法内容(connect)

//日期格式化
    function getTime(fmt){
        var date=new Date();
        var o = {
            "M+": date.getMonth() + 1,
            // 月份
            "d+": date.getDate(),
            // 日
            "h+": date.getHours(),
            // 小时
            "m+": date.getMinutes(),
            // 分
            "s+": date.getSeconds(),
            // 秒
            "q+": Math.floor((date.getMonth() + 3) / 3),
            // 季度
            "S": date.getMilliseconds()
            // 毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o) if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

//时间显示
    AddTime=function AddTime(user){
        var container =document.getElementById('historyMsg')
        var timeToDisplay = document.createElement('div');
        var date = new Date();
        var time = getTime("hh:mm:ss");
        timeToDisplay.innerHTML=time;
        if (user==="send"){///为用户发送
            timeToDisplay.className="timeRight";
        }else {
            timeToDisplay.className="timeLeft";
        }
        container.appendChild(timeToDisplay);
    }

//消息显示
    AddNewMsg=function AddNewMsg(user,msg){
        AddTime(user)
        var container =document.getElementById('historyMsg')
        //portrait
        var portrait=document.createElement('div');
        if(user==="send"){ ///为用户发送
            portrait.className="imgRight";   //头像

            portrait.style.background="url(/static/ZiXun_KeFu_User/images/1.png) no-repeat"; //用户更改

        }else{ ///否则为后台发的消息
            portrait.className="imgLeft"; //头像
        }

        container.appendChild(portrait);

        // arrow
        var arrow = document.createElement('div');
        if (user === 'send') {
            arrow.className = 'arrowRight';
        } else {
            arrow.className = 'arrowLeft';
        }
        container.appendChild(arrow);

        // send
        var send = document.createElement('div');
        // msg = this._showEmoji(msg);
        send.innerHTML = msg;
        if (user === "send") send.className = 'send';
        else send.className = 'receive';
        container.appendChild(send);

        //clear float
        var clearfloat=document.createElement('div');
        clearfloat.className='clearfloat';
        container.appendChild(clearfloat);

        container.scrollTop = container.scrollHeight;
    }

    $("#clearBtn").click(function () {
        document.getElementById('historyMsg').innerHTML=''

    })

//点击关闭
    $("#closeBtn").click(function () {
        // $("#clearBtn").click()
        webchat_close()
    })
    //点击发送
    $("#sendBtn").click(
        //输入框事件
        function SendClick(){
            const A=$("#messageInput");//绑定url
            const bt=$("#sendBtn");//绑定 前一步为什么操作
            var listId
            var listContent
            listId=questionId.data("questionId")
            listContent=questionContent.data('questionContent')

            // console.log(listId)
            // console.log(listContent)

            var messageInput = document.getElementById('messageInput');
            let msg = messageInput.innerHTML;
            messageInput.innerHTML = "";
            messageInput.focus();
            let url = '';
            let data={}
            if (msg.trim().length !== 0) {
                AddNewMsg('send',msg);
                // alert(listContent)
                // alert(msg)

                // let attribute = session.getAttribute("userId");
                // console.log("id"+a)

                if (listId.indexOf(msg)>-1){//有该问题
                    AddNewMsg('receive',listContent[listId.indexOf(msg)]);//进行问题的回复 通过 下标来查看
                }else {//没有该问题
                    if (msg==="我要留言"){//留言  是 1
                        //判断 用户是否登录
                        // let attribute = session.getAttribute("userId");
                        // console.log("id"+attribute)





                        bt.data('who',"1")//记录 上一步是什么操作
                        AddNewMsg('receive',"已进入留言系统");
                        AddNewMsg('receive',"请输入留言内容!");
                    }else{//非留言
                        if (bt.data('who')==="1"){//上次操作为留言
                            // url='/insertUserQuestion'
                            url='/ziXun/savaQuestion2'
                            // A.data('url',url) //存
                            data={"content":msg}
                            bt.data('who',"0")//恢复为问题模式

                        }else {
                            // url='/queryQuestionByTitle'
                            url='/ziXun/getKeyQuestion'
                            data={
                                "name":msg,
                                "page":1,
                                "limit":5,
                            }
                        }
                        $.ajax({
                            url: url,// 取
                            data: data,
                            dataType: 'json',
                            success: function (res) {
                                console.log(res)
                                if (res.head === "留言") {
                                    if (res.code === 200) {
                                        AddNewMsg('receive', res.msg);
                                    }else {
                                        AddNewMsg('receive', "留言失败，请检查后重试！");
                                    }
                                    AddNewMsg('receive', "已退出留言系统");
                                    url = '/ziXun/getKeyQuestion'
                                    A.data('url', url) //存


                                } else {
                                    if (res.code === '200') {
                                        // console.log(res.data.length)
                                        // console.log(res.data[1].content)//答案
                                        // console.log(res.data[1].id)
                                        // console.log(res.data[1].title) //问题
                                        // console.log(res.data[1].type) //标签

                                        var msg = '<span>' + "已为您检索到以下相关问题解答" + '</span>'
                                        var listid=[]
                                        var listcontent=[]


                                        for (let i = 0; i < res.data.length; i++) {
                                            listid.push(res.data[i].questionId+'')//向后追加数字id toString
                                            listcontent.push(res.data[i].content)//向后追加 答案content   这个是未避免重复查询数据
                                            msg += '<span>' + res.data[i].questionId + '  :  ' + res.data[i].name + '</span>'
                                        }
                                        questionId.data('questionId',listid)
                                        questionContent.data('questionContent',listcontent)
                                        AddNewMsg('receive', msg)
                                        console.log(msg)
                                        AddNewMsg('receive', "请回复问题编号查看解答!!")
                                    } else {
                                        AddNewMsg('receive', "暂无有关该问题的解答（请尝试向后台客服留言（若要留言，请回复：我要留言））")
                                    }
                                }
                            }
                        })
                    }
                }
            }else{
                showWarning()
            }
        })
    //输入框为空 错误提示
    function showWarning(){
        //弹出消息框，提示发送的消息不能为空
        //弹出消息框，提示发送的消息不能为空
        var warning = document.getElementById('warning');
        warning.style.display = 'block';
        setTimeout("warning.style.display = 'none';",'1500');
    }

    function webchat_close() {
        //默认开始的时候webchat是关闭的，只有按钮展示
        $('.float-webchat').animate({
            right:'-900px'
        }, 300, function(){
            $('.float-open').animate({
                right:'-1px'
            }, 300);
        });

    }

    //第一次热门问题
    function webchat_open() {
        //打开webchat,按钮隐藏
        $('.float-open').animate({
            right:'-80px'
        }, 300, function(){
            $('.float-webchat').animate({
                right:'0px'
            }, 300);
        });
        $.ajax({
            url:'/ziXun/getAllQuestion',
            data:{
                // int page, int limit
                page: 1,
                limit : 5
            },
            dataType:'json',
            success:function (res) {

                if (res.code===200){
                    // console.log(res.data.length)
                    // console.log(res.data[1].content)//答案
                    // console.log(res.data[1].id)
                    // console.log(res.data[1].name) //问题
                    // console.log(res.data[1].type) //标签

                    var msg = '<div>' + "热点·问题解答" + '</div>'
                    // var msg=''
                    var listid=[]
                    var listcontent=[]

                    for (let i=0; i<res.data.length; i++){
                        listid.push(res.data[i].questionId+'')//向后追加数字id
                        listcontent.push(res.data[i].content)//向后追加 答案content
                        msg+='<div>'+res.data[i].questionId+":"+ res.data[i].name+'</div>'
                    }
                    questionId.data('questionId',listid)
                    questionContent.data('questionContent',listcontent)

                    AddNewMsg('receive',msg)
                    AddNewMsg('receive',"请回复问题前编号查看问题解答!!")
                }
            }
        })
    }

    $('.float-close').click(function(){
        webchat_close();
        return false;
    });

    $('.open-btn').click(function(){
        webchat_open();
        return false;
    });
    setTimeout(function(){webchat_close()},100);
});
