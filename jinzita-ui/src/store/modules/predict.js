import Cookies from 'js-cookie'

const state = {
  predictCookie: '',
  userPhone: '',
  everyStatus: false,
}

const mutations = {
  setCookie: (state, cookie) => {
    state.predictCookie = cookie;
  },
  setUserPhone: (state, phone) => {
    state.userPhone = phone
  },
  setEveryStatus: (state, status) => {
    state.everyStatus = status;
  }
}
const actions = {
  SetCookie: ({commit}, {cookie, phone}) => {
    Cookies.set('douyin-token', cookie, {
      expires: 7
    })
    Cookies.set('douyin-phone', phone, {
      expires: 7,
    })
    commit('setCookie', cookie);
    commit('setUserPhone', phone);
  },
  GetCookie: ({ commit }) => {
    commit('setCookie', Cookies.get('douyin-token'));
    commit('setUserPhone', Cookies.get('douyin-phone'));
  },
  SetEveryStatus: ({ commit }, status) => {
    commit('setEveryStatus', status)
  }
}

export default {
  state,
  mutations,
  actions,
}
