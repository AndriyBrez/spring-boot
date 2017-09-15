var app = new Vue({
  el: '#app',
  data: {
    message: 'You loaded this page on ' + new Date().toLocaleString(),
    callResponse: 'NO REPONSE'
  },
  methods: {
    getIpList(){
        this.$http.get('stat').then(function(response){
          this.message = response.bodyText;
        });
    },
    callService(){
        this.$http.get('call').then(function (response){
            this.callResponse = response.bodyText;
        });
    },
    clearIpList(){
      this.message = "";
    }
  }

});