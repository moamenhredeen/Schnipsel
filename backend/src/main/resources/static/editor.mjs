import {basicSetup} from "codemirror"
import {EditorView, keymap} from "@codemirror/view"
import {EditorState} from "@codemirror/state"
import {indentWithTab} from "@codemirror/commands"

const theme = EditorView.theme({
    "&": {
        height: "600px",
    },
    ".cm-scroller": {
        overflow: "auto"
    }
})

export const editorView = new EditorView({
    state: EditorState.create({
        doc: document.getElementById('content').value,
        extensions: [
            EditorState.tabSize.of(4),
            keymap.of([indentWithTab]),
            basicSetup,
            theme,
        ],
    }),
    parent: document.getElementById('code-editor'),
})