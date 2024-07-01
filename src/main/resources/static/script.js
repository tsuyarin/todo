{
$(function() {
				//クリックして、書き込めるようにする。
			    let editing = false;		
			    $('[id^="list_"]').on("click", function() {
			        $(this).attr("contenteditable", "true");
			        $(this).focus();
			        editing = true;
			    });
			    
				//書き込みしたデータをサーバーに書き込む処理
			    $("[id^='list_']").on("blur", function() {
			        if (editing) {
			            let editedText = $(this).text();
			            let rowId = $(this).attr("id").split("_")[1];
			            if (rowId) {
			                $.ajax({
			                    url: "/write",
			                    method: "POST",
			                    data: { memo: editedText, id: rowId },
 								 success: function(data) {
   								  
			                    }
			                });
			            		            }
			            editing = false;
			        }
			    });
	
			    $("[id^='list_']").on("keydown", function(event) {
			        if (event.keyCode === 13) {
			            $(this).blur();
			            event.preventDefault();
			        }
			    });
				
			//並び順変更機能
			    $(".tbody").sortable({
			        start: function(event, ui) {
			            editing = false;
			        },
			        update: function(event, ui) {
			            let sortedIDs = [];
						$(this).find("tr").each(function() {
						    $(this).find("td").each(function() {
						        var id = $(this).attr("id");
						        if (id) {
						            sortedIDs.push(id.split("_")[1]);
						        }
						    });
						});
						
						$.ajax({
						    url: "/move",
						    method: "POST",
						    data:{ sortedIDs: sortedIDs}, 
 								 success: function(data) {
   								  location.reload();
						    }
						});
	
			        }
			    }).disableSelection();
				
				//チェックボタン
				$('[id^="checkbox_"]').on('click', function(){	
						let check = $(this).prop("checked");
						check==true ? check=1 : check=0;
						let rowId = $(this).attr("id").split("_")[1];
					
						$.ajax({
							url:"/check",
							method: "POST",
							data:{ check: check, rowid: rowId },
 							success: function(data) {
   								   location.reload();
							}

					});
				});	
				
				//削除ボタン
				$('[id^="delete_"]').on('click', function(){					
					let rowId = $(this).attr("id").split("_")[1];
					$.ajax({
						url:"/deletelist",
						method: "POST",
						data:{rowid: rowId },
 							success: function(data) {
   								  location.reload();
						}
					});
				});
				
				
				//タブをクリックして、書き込めるようにする。★★したとかぶるので処理が動くかまず見る。アラート出す?
			$(document).ready(function() {
				let tabediting =false;
				$("[id^='tabs_']").on("click",function(){
					$(this).atrr("contenteditable", "true");
					$(this).focus();
					tabediting = true;
				});
				
				$("[id^='tabs_'']").on("blur", function() {
			        if (editing) {
			            let editedText = $(this).text();
			            let rowId = $(this).attr("id").split("_")[1];
			            if (rowId) {
			                $.ajax({
			                    url: "/writetab",
			                    method: "POST",
			                    data: { memo: editedText, id: rowId },
 							success: function(data) {
   								  
			                    }
			                });
			            		            }
			            editing = false;
			        }
			    });
				
				
				//タブの切り替え
				$(".tab_menu-item-content").on('click', function(){
					let tabid =$(this).closest(".tab_menu-item").attr("id").split("_")[1];
					$('.tab_menu-item-content.-active').removeClass('-active');
					$(this).addClass('-active');
					$.ajax({
						url:"/tab",
						method: "POST",
						data:{tabid: tabid },
 							success: function(data) {
   								  
						}
					});
				});
				
				

				//タブの新規作成
				$("#newtab").on('click',function(){
					    $.ajax({
							url:"/newtab",
							method: "POST",
 							success: function(data) {
   								  
							}
						})
				});
				
				
				//タブの削除切り替えてないのは押せないようにする。
				$('[class^="tabdelete_"]').on('click',function(){
					let tabposision =$(this).attr("class").split("_")[1];
			
						const yesNoFlg = window.confirm("このタブ内のすべてのリストが削除されます、よろしいですか？");
																																			
																		
						if (yesNoFlg) {
							$.ajax({
								url:"/deletetab",
								method: "POST",
								data:{tabposision: tabposision},
 							success: function(data) {
   								  
								}
							})
						}	

				

					
				});
				
				
			});
			
			//新規作成リスト
			$("#newlist").on('click', function(){
			    $.ajax({
					url:"/newlist",
					method: "POST",
					success: function(data) {
						  location.reload();
					}
				})
			});
	
	
			
			
			
		
	});
}