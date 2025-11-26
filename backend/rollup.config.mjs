import {nodeResolve} from "@rollup/plugin-node-resolve"
export default {
    input: "./src/main/resources/static/editor.mjs",
    output: {
        file: "./src/main/resources/static/editor.bundle.js",
        format: "iife",
        name: "CodeEditor"
    },
    plugins: [nodeResolve()]
}
