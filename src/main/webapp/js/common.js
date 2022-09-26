function confirmCommand(url, command) {
	if (window.confirm(command + "하시겠습니까?")) {
		location.href = url;
	} else {
		return false;
	}
}