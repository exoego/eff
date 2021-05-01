;(() => {
    const sidebarHtml = `
        <!-- search box -->
        <div class="search-box">
        </div>

        <!-- table of contents -->
        <div id="tree">
    <ul>
        <li id="index"><a href="index.html" title="eff">eff</a>
            <ul>
                <li><a href="index.html#contributing" title="貢献">貢献</a></li>
            </ul>
        </li>
        <li id="org-atnos-site-installation"><a href="org.atnos.site.Installation.html"
                                                title="インストール">インストール</a>
            <ul>
                <li><a href="org.atnos.site.Installation.html#additional-dependencies"
                       title="さらなる依存関係">さらなる依存関係</a></li>
                <li><a href="org.atnos.site.Installation.html#imports" title="インポート">インポート</a>
                    <ul>
                        <li><a href="org.atnos.site.Installation.html#main-types" title="主要な型">主要な型</a></li>
                        <li><a href="org.atnos.site.Installation.html#creating-effects"
                               title="エフェクトの作成">エフェクトの作成</a></li>
                        <li><a href="org.atnos.site.Installation.html#interpreting-effects"
                               title="エフェクトの解釈">エフェクトの解釈</a></li>
                        <li><a href="org.atnos.site.Installation.html#with-scalaz"
                               title="Scalaz と使う">Scalaz と使う</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-introduction"><a href="org.atnos.site.Introduction.html"
                                                title="初めてのエフェクト">初めてのエフェクト</a>
            <ul>
                <li><a href="org.atnos.site.Introduction.html#first-example"
                       title="最初の例">最初の例</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-outofthebox"><a href="org.atnos.site.OutOfTheBox.html"
                                               title="すぐ使えるエフェクト">すぐ使えるエフェクト</a>
            <ul>
                <li><a href="org.atnos.site.OutOfTheBox.html#what’s-next?" title="次にすることは？">次にすることは？</a></li>
                <li id="org-atnos-site-lib-evaleffectpage"><a
                        href="org.atnos.site.lib.EvalEffectPage.html" title="Eval">Eval</a></li>
                <li id="org-atnos-site-lib-optioneffectpage"><a
                        href="org.atnos.site.lib.OptionEffectPage.html"
                        title="Option">Option</a></li>
                <li id="org-atnos-site-lib-eithereffectpage"><a
                        href="org.atnos.site.lib.EitherEffectPage.html"
                        title="Either">Either</a>
                </li>
                <li id="org-atnos-site-lib-validateeffectpage"><a
                        href="org.atnos.site.lib.ValidateEffectPage.html" title="Validate">Validate</a>
                </li>
                <li id="org-atnos-site-lib-erroreffectpage"><a
                        href="org.atnos.site.lib.ErrorEffectPage.html" title="Error">Error</a>
                </li>
                <li id="org-atnos-site-lib-readereffectpage"><a
                        href="org.atnos.site.lib.ReaderEffectPage.html"
                        title="Reader">Reader</a>
                </li>
                <li id="org-atnos-site-lib-writereffectpage"><a
                        href="org.atnos.site.lib.WriterEffectPage.html"
                        title="Writer">Writer</a>
                </li>
                <li id="org-atnos-site-lib-stateeffectpage"><a
                        href="org.atnos.site.lib.StateEffectPage.html" title="State">State</a>
                </li>
                <li id="org-atnos-site-lib-listeffectpage"><a
                        href="org.atnos.site.lib.ListEffectPage.html" title="List">List</a>
                </li>
                <li id="org-atnos-site-lib-chooseeffectpage"><a
                        href="org.atnos.site.lib.ChooseEffectPage.html"
                        title="Choose">Choose</a>
                </li>
                <li id="org-atnos-site-lib-memoeffectpage"><a
                        href="org.atnos.site.lib.MemoEffectPage.html" title="Memo">Memo</a>
                </li>
                <li id="org-atnos-site-lib-timedfutureeffectpage"><a
                        href="org.atnos.site.lib.TimedFutureEffectPage.html"
                        title="TimedFuture">TimedFuture</a>
                </li>
                <li id="org-atnos-site-lib-taskeffectpage"><a
                        href="org.atnos.site.lib.TaskEffectPage.html" title="Task">Task</a>
                </li>
                <li id="org-atnos-site-lib-safeeffectpage"><a
                        href="org.atnos.site.lib.SafeEffectPage.html" title="Safe">Safe</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-tutorial"><a href="org.atnos.site.Tutorial.html" title="チュートリアル">チュートリアル</a>
            <ul>
                <li><a href="org.atnos.site.Tutorial.html#study-your-topic" title="自分のテーマを深く知る">自分のテーマを深く知る</a>
                </li>
                <li>
                    <a href="org.atnos.site.Tutorial.html#create-an-adt-representing-your-grammar"
                       title="自分の文法を表す ADT を作る">文法を表す ADT を作る</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#free-your-adt" title="ADT を自由にする">ADT
                    を自由にする</a>
                </li>
                <li>
                    <a href="org.atnos.site.Tutorial.html#create-smart-constructors-using-eff.send"
                       title="Eff.send でスマートコンストラクタを作る">Eff.send でスマート…</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#build-a-program" title="プログラムを作る">プログラムを作る</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#write-an-interpreter-for-your-program"
                       title="インタープリターを書く">インタープリターを書く</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#run-your-program" title="プログラムを実行する">プログラムを実行する</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#add-some-syntax" title="シンタックスを追加する">シンタックスを追加する</a>
                </li>
                <li><a href="org.atnos.site.Tutorial.html#composing-adts-with-the-eff-monad"
                       title="Eff モナドで ADT を合成する">Eff モナドで ADT 合成</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-createeffects"><a href="org.atnos.site.CreateEffects.html"
                                                 title="エフェクトの作成">エフェクトの作成</a>
            <ul>
                <li><a href="org.atnos.site.CreateEffects.html#creation" title="作成">作成</a>
                </li>
                <li><a href="org.atnos.site.CreateEffects.html#compiler-limitation"
                       title="コンパイラーの制限">コンパイラーの制限</a>
                </li>
                <li><a href="org.atnos.site.CreateEffects.html#interpreter"
                       title="インタープリター">インタープリター</a>

                </li>
            </ul>
        </li>

        <li id="org-atnos-site-transformstack"><a href="org.atnos.site.TransformStack.html"
                                                  title="Transform stacks">Transform stacks</a>
            <ul>
                <li><a href="org.atnos.site.TransformStack.html#what-is-an-“effect-stack”?"
                       title="What is an “effect stack”?">What is an “eff...</a>
                </li>
                <li><a href="org.atnos.site.TransformStack.html#transform-an-effect-to-another"
                       title="Transform an effect to another">Transform an ef...</a>
                </li>
                <li>
                    <a href="org.atnos.site.TransformStack.html#translate-an-effect-into-multiple-others"
                       title="Translate an effect into multiple others">Translate an ef...</a>
                </li>
                <li><a href="org.atnos.site.TransformStack.html#interpret-an-effect-“locally”"
                       title="Interpret an effect “locally”">Interpret an ef...</a>
                </li>
                <li><a href="org.atnos.site.TransformStack.html#merge-stacks"
                       title="Merge stacks">Merge stacks</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-memberimplicits"><a href="org.atnos.site.MemberImplicits.html"
                                                   title="Member implicits">Member implicits</a>
            <ul>
                <li>
                    <a href="org.atnos.site.MemberImplicits.html#running-effects-with-several-type-parameters"
                       title="Running effects with several type parameters">Running
                        effects...</a>
                </li>
                <li>
                    <a href="org.atnos.site.MemberImplicits.html#use-context-bounds-and-type-aliases"
                       title="Use context bounds and type aliases">Use context bou...</a>

                </li>
                <li><a href="org.atnos.site.MemberImplicits.html#know-your-member-typeclasses"
                       title="Know your Member typeclasses">Know your Membe...</a>

                </li>
                <li><a href="org.atnos.site.MemberImplicits.html#“packing”-member-instances"
                       title="“Packing” member instances">“Packing” membe...</a>

                </li>
            </ul>
        </li>
        <li id="org-atnos-site-applicativeevaluation"><a
                href="org.atnos.site.ApplicativeEvaluation.html" title="Applicative">Applicative</a>
            <ul>
                <li><a href="org.atnos.site.ApplicativeEvaluation.html#concurrent-evaluation"
                       title="Concurrent evaluation">Concurrent eval...</a>

                </li>
                <li><a href="org.atnos.site.ApplicativeEvaluation.html#batching"
                       title="Batching">Batching</a>

                </li>
            </ul>
        </li>
        <li id="org-atnos-site-cookbook"><a href="org.atnos.site.Cookbook.html" title="Cookbook">Cookbook</a>
            <ul>
                <li><a href="org.atnos.site.Cookbook.html#partial-interpretation"
                       title="Partial Interpretation">Partial Interpr...</a>
                </li>
            </ul>
        </li>
        <li id="org-atnos-site-communityresources"><a href="org.atnos.site.CommunityResources.html"
                                                      title="Community resources">Community
            resou...</a>
            <ul>
                <li><a href="org.atnos.site.CommunityResources.html#blog-posts"
                       title="Blog posts">Blog posts</a>
                </li>
                <li><a href="org.atnos.site.CommunityResources.html#tutorials-&amp;-examples"
                       title="Tutorials &amp; Examples">Tutorials &amp; Exa...</a>

                </li>
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
        footer.className = "container";
        footer.innerHTML = footerHtml;
        document.body.appendChild(footer);

        const sidebar = document.createElement("div");
        sidebar.className = "fixed";
        sidebar.innerHTML = sidebarHtml;
        const sidebarOut = document.querySelector(".sidebar-outer");
        sidebarOut.appendChild(sidebar);

        const activeLink = sidebarOut.dataset.active || '';
        document.getElementById(activeLink).className += "active"

        const target = document.getElementById(activeLink);
        const parentNode = document.getElementById("tree")
        parentNode.scrollTop = target.offsetTop - parentNode.offsetTop;
    });
})();

