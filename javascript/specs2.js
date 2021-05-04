(() => {
  const sidebarHtml = `
        <!-- search box -->
        <div class="search-box">
        </div>

        <!-- table of contents -->
        <div id="tree">
    <ul>
        <li id="index"><a href="index.html">eff</a>
            <ul>
                <li><a href="index.html#contributing">貢献</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-installation">
            <a href="org.atnos.site.Installation.html">インストール</a>
            <ul>
                <li><a href="org.atnos.site.Installation.html#additional-dependencies">さらなる依存関係</a></li>
                <li><a href="org.atnos.site.Installation.html#imports">インポート</a>
                    <ul>
                        <li><a href="org.atnos.site.Installation.html#main-types">主要な型</a></li>
                        <li><a href="org.atnos.site.Installation.html#creating-effects">エフェクトの作成</a></li>
                        <li><a href="org.atnos.site.Installation.html#interpreting-effects">エフェクトの解釈</a></li>
                        <li><a href="org.atnos.site.Installation.html#with-scalaz">Scalaz と使う</a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-introduction">
            <a href="org.atnos.site.Introduction.html">初めてのエフェクト</a>
            <ul>
                <li><a href="org.atnos.site.Introduction.html#first-example">最初の例</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-outofthebox">
            <a href="org.atnos.site.OutOfTheBox.html">すぐ使えるエフェクト</a>
            <ul>
                <li id="org-atnos-site-lib-evaleffectpage"><a href="org.atnos.site.lib.EvalEffectPage.html">Eval</a></li>
                <li id="org-atnos-site-lib-optioneffectpage"><a href="org.atnos.site.lib.OptionEffectPage.html">Option</a></li>
                <li id="org-atnos-site-lib-eithereffectpage"><a href="org.atnos.site.lib.EitherEffectPage.html">Either</a></li>
                <li id="org-atnos-site-lib-validateeffectpage"><a href="org.atnos.site.lib.ValidateEffectPage.html">Validate</a></li>
                <li id="org-atnos-site-lib-erroreffectpage"><a href="org.atnos.site.lib.ErrorEffectPage.html">Error</a></li>
                <li id="org-atnos-site-lib-readereffectpage"><a href="org.atnos.site.lib.ReaderEffectPage.html">Reader</a></li>
                <li id="org-atnos-site-lib-writereffectpage"><a href="org.atnos.site.lib.WriterEffectPage.html">Writer</a></li>
                <li id="org-atnos-site-lib-stateeffectpage"><a href="org.atnos.site.lib.StateEffectPage.html">State</a></li>
                <li id="org-atnos-site-lib-listeffectpage"><a href="org.atnos.site.lib.ListEffectPage.html">List</a></li>
                <li id="org-atnos-site-lib-chooseeffectpage"><a href="org.atnos.site.lib.ChooseEffectPage.html">Choose</a></li>
                <li id="org-atnos-site-lib-memoeffectpage"><a href="org.atnos.site.lib.MemoEffectPage.html">Memo</a></li>
                <li id="org-atnos-site-lib-timedfutureeffectpage"><a href="org.atnos.site.lib.TimedFutureEffectPage.html">TimedFuture</a></li>
                <li id="org-atnos-site-lib-taskeffectpage"><a href="org.atnos.site.lib.TaskEffectPage.html">Task</a></li>
                <li id="org-atnos-site-lib-safeeffectpage"><a href="org.atnos.site.lib.SafeEffectPage.html">Safe</a></li>
                <li><a href="org.atnos.site.OutOfTheBox.html#what’s-next?">次にすることは？</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-tutorial"><a href="org.atnos.site.Tutorial.html">チュートリアル</a>
            <ul>
                <li><a href="org.atnos.site.Tutorial.html#study-your-topic">自分のテーマを深く知る</a></li>
                <li><a href="org.atnos.site.Tutorial.html#create-an-adt-representing-your-grammar">文法を表す ADT を作る</a></li>
                <li><a href="org.atnos.site.Tutorial.html#free-your-adt">ADT を自由にする</a></li>
                <li><a href="org.atnos.site.Tutorial.html#create-smart-constructors-using-eff.send">Eff.send でスマートコンストラクタを作る</a></li>
                <li><a href="org.atnos.site.Tutorial.html#build-a-program">プログラムを作る</a></li>
                <li><a href="org.atnos.site.Tutorial.html#write-an-interpreter-for-your-program">インタープリターを書く</a></li>
                <li><a href="org.atnos.site.Tutorial.html#run-your-program">プログラムを実行する</a></li>
                <li><a href="org.atnos.site.Tutorial.html#add-some-syntax">シンタックスを追加する</a></li>
                <li><a href="org.atnos.site.Tutorial.html#composing-adts-with-the-eff-monad">Eff モナドで ADT 合成</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-createeffects">
            <a href="org.atnos.site.CreateEffects.html">エフェクトの作成</a>
            <ul>
                <li><a href="org.atnos.site.CreateEffects.html#creation">作成</a></li>
                <li><a href="org.atnos.site.CreateEffects.html#compiler-limitation">コンパイラーの制限</a></li>
                <li><a href="org.atnos.site.CreateEffects.html#interpreter">インタープリター</a></li>
            </ul>
        </li>

        <li id="org-atnos-site-transformstack">
            <a href="org.atnos.site.TransformStack.html">エフェクトスタックを変換する</a>
            <ul>
                <li><a href="org.atnos.site.TransformStack.html#what-is-an-effect-stack">エフェクトスタックとは？</a></li>
                <li><a href="org.atnos.site.TransformStack.html#transform-an-effect-to-another">他のエフェクトに変換する</a></li>
                <li><a href="org.atnos.site.TransformStack.html#translate-an-effect-into-multiple-others">複数のエフェクトに変換する</a></li>
                <li><a href="org.atnos.site.TransformStack.html#interpret-an-effect-locally">エフェクトを局所的に解釈する</a></li>
                <li><a href="org.atnos.site.TransformStack.html#merge-stacks">スタックをマージする</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-memberimplicits">
            <a href="org.atnos.site.MemberImplicits.html">メンバー implicits</a>
            <ul>
                <li><a href="org.atnos.site.MemberImplicits.html#running-effects-with-several-type-parameters">型パラメータが複数あるエフェクトを実行する</a></li>
                <li><a href="org.atnos.site.MemberImplicits.html#use-context-bounds-and-type-aliases">context bound と型エイリアスを使う</a></li>
                <li><a href="org.atnos.site.MemberImplicits.html#know-your-member-typeclasses">Member 型クラスを知る</a></li>
                <li><a href="org.atnos.site.MemberImplicits.html#packing-member-instances">Member インスタンスを圧縮する</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-applicativeevaluation">
            <a href="org.atnos.site.ApplicativeEvaluation.html">「応用」編</a>
            <ul>
                <li><a href="org.atnos.site.ApplicativeEvaluation.html#concurrent-evaluation">並列評価</a></li>
                <li><a href="org.atnos.site.ApplicativeEvaluation.html#batching">一括処理</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-cookbook">
            <a href="org.atnos.site.Cookbook.html">レシピ集</a>
            <ul>
                <li><a href="org.atnos.site.Cookbook.html#partial-interpretation">部分的な解釈</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-communityresources">
            <a href="org.atnos.site.CommunityResources.html">コミュニティリソース</a>
            <ul>
                <li><a href="org.atnos.site.CommunityResources.html#blog-posts">ブログ記事</a></li>
                <li><a href="org.atnos.site.CommunityResources.html#tutorials-examples">チュートリアルと実例</a></li>
            </ul>
        </li>
    </ul>
</div>
    `;

  const filepath = location.pathname.replace(/^\/eff\//, "");
  const englishUrl = `https://atnos-org.github.io/eff/${filepath}`;
  const editUrl = `https://github.com/exoego/eff/edit/gh-pages/${filepath}`;

  const footerHtml = `
        <hr>
        <p><a href="${editUrl}">このページを修正</a> | <a href="${englishUrl}">原文</a> | 翻訳に関する貢献は <a href="https://github.com/exoego/eff">exoego/eff</a> まで</p>
        <p>Licensed under <a href="http://opensource.org/licenses/mit-license.html">MIT License</a></p>
        <p></p>
    `;

  window.addEventListener("DOMContentLoaded", function (event) {
    prettyPrint();

    const footer = document.createElement("div");
    footer.className = "container-fluid";
    footer.innerHTML = footerHtml;
    document.body.appendChild(footer);

    const sidebar = document.createElement("div");
    sidebar.className = "fixed";
    sidebar.innerHTML = sidebarHtml;
    const sidebarOut = document.querySelector(".sidebar-outer");
    sidebarOut.appendChild(sidebar);

    const activeLinkId = location.pathname
      .replace(/\/eff\/|\.html/g, "")
      .replaceAll(".", "-")
      .toLowerCase();
    const activeLink = document.getElementById(activeLinkId);
    if (activeLink) {
      activeLink.className += "active";
      const parentNode = document.getElementById("tree");
      parentNode.scrollTop = Math.max(
        activeLink.offsetTop - parentNode.offsetTop - 50,
        0
      );
    }
  });
})();
