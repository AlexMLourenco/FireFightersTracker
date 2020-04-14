export default {
  setDrawer({commit}, drawer) {
    commit('SET_DRAWER', drawer)
  },
  setImage({commit}, image) {
    commit('SET_IMAGE', image)
  },
  setColor({commit}, color) {
    commit('SET_COLOR', color);
  },
  get_firefighters: async function({ commit }) {
    var res = {}
    try {
        var res = await axios({
            method: 'get',
            url: getUrl() + '/fighters',
        }) 
    } catch(err) {  
        console.log(err);
        commit('SET_STORE', {err: ''})
    } finally {
        const data = await startup(axios)
        console.log(data)
        commit('SET_STORE', {...res.data, ...data})
    }
}
}
