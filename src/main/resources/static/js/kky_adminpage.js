function selectAll(selectAll)  {
  const checkboxes 
     = document.querySelectorAll('input[type="checkbox"]');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
}

function fn_userDelete() {
	var deleteUser = document.admin_userlist;
	var isUserlistChk = false;
    var arr_Userlist = document.getElementsByName("userlist_id");
	let deleteCheck = false;

    for(var i = 0; i < arr_Userlist.length; i++) {
        if(arr_Userlist[i].checked == true) {
            isUserlistChk = true;
            break;
        }
    }

    if(!isUserlistChk){
        alert("삭제할 회원을 선택해주세요.");
        return;
    }
	
	if (!confirm("정말 삭제하시겠습니까?")) {
        deleteCheck = false;
    } else {
        deleteCheck = true;
    }

	if (deleteCheck == false) {
		return;
	} else {
		deleteUser.method = "post";
		deleteUser.action = "/admin/delete.do";
		deleteUser.submit();
	}
	
}

function fn_findId() {
	var findUser = document.findUser;
	findUser.method = "post";
	findUser.action = "/admin/findId";
	findUser.submit();
}

function fn_productDelete() {
	var deleteProduct = document.admin_productlist;
	var isUserlistChk = false;
    var arr_Productlist = document.getElementsByName("productlist_id");
	let deleteCheck = false;

    for(var i = 0; i < arr_Userlist.length; i++) {
        if(arr_Productlist[i].checked == true) {
            isUserlistChk = true;
            break;
        }
    }

    if(!isUserlistChk){
        alert("삭제할 회원을 선택해주세요.");
        return;
    }
	
	if (!confirm("정말 삭제하시겠습니까?")) {
        deleteCheck = false;
    } else {
        deleteCheck = true;
    }

	if (deleteCheck == false) {
		return;
	} else {
		deleteProduct.method = "post";
		deleteProduct.action = "/admin/deleteproduct.do";
		deleteProduct.submit();
	}
	
}

function fn_findProduct() {
	var findProduct = document.findProduct;
	findProduct.method = "post";
	findProduct.action = "/admin/findProduct";
	findProduct.submit();
}
