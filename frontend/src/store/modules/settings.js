const settings = {
  namespaced: true,
  state: () => ({
    count: 0,
    theme: localStorage.getItem("theme") || 'light',
  }),
  mutations: {
    changeTheme(state, val) {
      localStorage.setItem("theme", val);
      state.theme = val;
    },
  },
  actions: {},
  getters: {}
}
export default settings
