function logoutSubmit() {
    const form = document.createElement("form");
    form.method = "POST";
    form.action = "/logout";
    document.body.append(form);
    form.submit();
}