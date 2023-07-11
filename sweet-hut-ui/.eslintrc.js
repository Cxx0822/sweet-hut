module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: [
    'eslint:recommended',
    'plugin:vue/essential',
    'plugin:@typescript-eslint/recommended',
    // 引入阿里巴巴前端代码规范 https://github.com/alibaba/f2e-spec
    './src/rules/base/best-practices',
    './src/rules/base/possible-errors',
    './src/rules/base/style',
    './src/rules/base/variables',
    './src/rules/base/es6',
    './src/rules/base/strict',
    './src/rules/vue',
    './src/rules/typescript',
  ],
  parser: 'vue-eslint-parser', /* 解析 .vue 文件 */
  parserOptions: {
    ecmaVersion: 12,
    parser: '@typescript-eslint/parser',
    sourceType: 'module',
  },
  plugins: [
    'vue',
    '@typescript-eslint',
  ],
}
