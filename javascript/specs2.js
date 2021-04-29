window.addEventListener("DOMContentLoaded", function (event) {
    const footer = document.createElement("div");
    footer.className = "container";
    const filepath = location.pathname.replace(/^\/eff\//, "");
    const englishUrl = `https://atnos-org.github.io/eff/${filepath}`;
    const editUrl = `https://github.com/exoego/eff/edit/gh-pages/${filepath}`;
    footer.innerHTML = `
        <hr>
        <p><a href="${editUrl}">このページを修正</a> | <a href="${englishUrl}">原文</a> | 翻訳に関する貢献は <a href="https://github.com/exoego/eff">exoego/eff</a> まで</p>
        <p>Licensed under <a href="http://opensource.org/licenses/mit-license.html">MIT License</a></p>
        <p></p>
    `;
    document.body.appendChild(footer);
});
